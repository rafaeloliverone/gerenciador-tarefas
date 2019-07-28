package br.com.rafaeloliveira.gerenciadortarefas.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.rafaeloliveira.gerenciadortarefas.models.Tasks;
import br.com.rafaeloliveira.gerenciadortarefas.repositories.TaskRepository;

@Controller
@RequestMapping("/tarefas")
public class TasksController {

	// Para listar todas as tarefas, irá precisar acessar o repositório
	// TaskRepository, não tem como instancia-lo pois ele é uma interface
	// sendo assim chama o Wired e atribui a uma variável, injeção de dependências
	// se chama isso, onde não precisar instanciar os objetos.
	@Autowired
	private TaskRepository taskRepositor;

	@GetMapping("/listar")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/listar");
		mv.addObject("tarefas", taskRepositor.findAll());
		return mv;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tasks());
		return mv;
	}
	
//	Vai receber um objeto tarefa, que vai vim do inserir.html e vai salvar no repositório que já está na classe.
	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tasks tarefa, BindingResult bindingResult ){
		ModelAndView mv = new ModelAndView();
		
		if (tarefa.getExpirationDate() == null) {
			bindingResult.rejectValue("expirationDate", "tarefa.expirationDateInvalida", "Data de expiração é obrigatória");
		} else {
			if (tarefa.getExpirationDate().before(new Date())) {
				bindingResult.rejectValue("expirationDate", "tarefa.expirationDateInvalida", "Data de expiração não pode ser anterior à data atual");
			}
		}
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject("tarefa", tarefa);
        } else {
        	mv.setViewName("redirect:/tarefas/listar");
        	taskRepositor.save(tarefa);
        }
		return mv;	
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Tasks tarefa = taskRepositor.getOne(id);		
		mv.addObject("tarefa", tarefa);
		mv.setViewName("tarefas/alterar");
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tasks tarefa, BindingResult bindingResult ){
		ModelAndView mv = new ModelAndView();
		
		if (tarefa.getExpirationDate() == null) {
			bindingResult.rejectValue("expirationDate", "tarefa.expirationDateInvalida", "Data de expiração é obrigatória");
		} else {
			if (tarefa.getExpirationDate().before(new Date())) {
				bindingResult.rejectValue("expirationDate", "tarefa.expirationDateInvalida", "Data de expiração não pode ser anterior à data atual");
			}
		}
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("tarefas/alterar");
			mv.addObject("tarefa", tarefa);
        } else {
        	mv.setViewName("redirect:/tarefas/listar");
        	taskRepositor.save(tarefa);
        }
		return mv;
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		taskRepositor.deleteById(id);
		return "redirect:/tarefas/listar";
	}
	
	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id") Long id) {
		Tasks tarefa = taskRepositor.getOne(id);
		tarefa.setDone(true);
		taskRepositor.save(tarefa);
		return "redirect:/tarefas/listar";
	}
		
}
