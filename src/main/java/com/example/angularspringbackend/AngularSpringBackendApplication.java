package com.example.angularspringbackend;

import com.example.angularspringbackend.enums.Category;
import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AngularSpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularSpringBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CursoRepository cursoRepository) {
		return args -> {
			cursoRepository.deleteAll();

			Curso c = new Curso();
			c.setDsNome("Angular");
			c.setFgCategory(Category.FRONTEND);
			cursoRepository.save(c);
		};
	}
}
