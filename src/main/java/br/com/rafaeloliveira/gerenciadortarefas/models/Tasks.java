package br.com.rafaeloliveira.gerenciadortarefas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tas_tasks")
public class Tasks {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tas_id")
	private Long id;
	
	@Column(name = "tas_title", length = 50, nullable = false)
	private String title;
	
	@Column(name = "tas_description", length = 100, nullable = true)
	private String description;
	
	@Column(name = "tas_expiration_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	
	@Column(name = "tas_done", nullable = false)
	private Boolean done = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

}