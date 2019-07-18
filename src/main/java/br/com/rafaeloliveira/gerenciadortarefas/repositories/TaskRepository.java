package br.com.rafaeloliveira.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaeloliveira.gerenciadortarefas.models.Tasks;

//Quando a aplicação estiver rodando, o spring vai gerar essa classe, 
//com todos os métodos tradicionais de manipulação de uma tabela(insert, update ..), 
//basta que essa interface extenda JpaRepository, passando o modelo e o tipo da primary key 
public interface TaskRepository extends JpaRepository<Tasks, Long>{
	
}
