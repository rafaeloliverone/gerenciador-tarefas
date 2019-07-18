package br.com.rafaeloliveira.gerenciadortarefas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ModelAndView insert() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tasks());
		return mv;
	}
	
//	Vai receber um objeto tarefa, que vai vim do inserir.html e vai salvar no repositório que já está na classe.
	@PostMapping("/inserir")
	public String inserir(Tasks task) {
		taskRepositor.save(task);
		return "redirect:/tarefas/listar";
	}
	
}
