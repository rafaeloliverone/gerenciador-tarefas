package br.com.rafaeloliveira.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaeloliveira.gerenciadortarefas.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

//	select where email = email passado no argumento
	Users findByEmail(String email);

}
