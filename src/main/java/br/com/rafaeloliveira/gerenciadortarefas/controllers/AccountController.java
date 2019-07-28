package br.com.rafaeloliveira.gerenciadortarefas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.rafaeloliveira.gerenciadortarefas.models.Users;
import br.com.rafaeloliveira.gerenciadortarefas.services.ServiceUser;

@Controller
public class AccountController {

	@Autowired
	private ServiceUser serviceUser;	
	
	@GetMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account/register");
		mv.addObject("user", new Users());
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registration(@Valid Users user, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		Users usr = serviceUser.encontrarPorEmail(user.getEmail());
		
		if (usr != null) {
			System.out.println("USUÁRIO CADASTRADO");
			System.out.println("USUÁRIO CADASTRADO");
			System.out.println("USUÁRIO CADASTRADO");
			result.rejectValue("email", "", "Usuário já cadastrado");
		} 
		
		if (user.getPassword() == null) {
			System.out.println("SENHAAA ERROO");
			result.rejectValue("password", "", "password vazio");
		}
		
		if (result.hasErrors()) {
			mv.setViewName("account/register");
			System.out.println("RESULT HAS ERROR");
			System.out.println("RESULT HAS ERROR");
			System.out.println("RESULT HAS ERROR");
			mv.addObject("user", user);
		} else  {
			System.out.println("SALVANDOOO");
			System.out.println("SALVANDOOO");
			System.out.println("SALVANDOOO");
			serviceUser.salvar(user);
			mv.setViewName("redirect:/login");
			
			
		}
		
		
		return mv;
	}
	
	
	
	
}
