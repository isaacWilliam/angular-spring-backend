package com.example.angularspringbackend;

import com.example.angularspringbackend.enums.Category;
import com.example.angularspringbackend.model.Aula;
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

			for (int i = 0; i < 100; i++) {
				Curso c = new Curso();
				c.setDsNome("Angular " + i);
				c.setFgCategory(Category.FRONTEND);

				Aula a = new Aula();
				a.setDsNome("Introduçao");
				a.setDsYouTube("watch?v=1");
				a.setCurso(c);
				c.getAulas().add(a);
				cursoRepository.save(c);

				Aula a1 = new Aula();
				a1.setDsNome("Instalação");
				a1.setDsYouTube("watch?v=2");
				a1.setCurso(c);
				c.getAulas().add(a1);
				cursoRepository.save(c);
			}
		};
	}
}
