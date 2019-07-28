package br.com.rafaeloliveira.gerenciadortarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rafaeloliveira.gerenciadortarefas.models.Users;
import br.com.rafaeloliveira.gerenciadortarefas.repositories.UsersRepository;

@Service
public class ServiceUser {

//	Já que é uma interface o Spring cria o objeto em tempo de execução
	@Autowired
	private UsersRepository repositoryUsers;

//	passwordEncoder é um Bean="Objetos que podem ser injetados", o java não sabe criar um objeto da classe BCrypt, preciso ensiná-lo com uma classe de configuração WebMvc. 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Users encontrarPorEmail(String email) {
		return repositoryUsers.findByEmail(email);
	}
	
	public void salvar(Users user) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			repositoryUsers.save(user);
		
	}
}
