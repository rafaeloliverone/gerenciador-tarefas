package br.com.rafaeloliveira.gerenciadortarefas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rafaeloliveira.gerenciadortarefas.models.Tasks;

//Quando a aplicação estiver rodando, o spring vai gerar essa classe, 
//com todos os métodos tradicionais de manipulação de uma tabela(insert, update ..), 
//basta que essa interface extenda JpaRepository, passando o modelo e o tipo da primary key 
public interface TaskRepository extends JpaRepository<Tasks, Long>{
	
//	consulta jpql
	@Query("SELECT t FROM Tasks t WHERE t.user.email = :emailUser")
	List<Tasks> loadTasksByUsers(@Param("emailUser") String email);
}
