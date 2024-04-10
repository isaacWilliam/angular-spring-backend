package com.example.angularspringbackend.dto.mapper;

import com.example.angularspringbackend.dto.AulaDTO;
import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.enums.Category;
import com.example.angularspringbackend.model.Aula;
import com.example.angularspringbackend.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        List<AulaDTO> aulaDTOS = curso.getAulas()
                .stream().map(aula -> new AulaDTO(aula.getId(), aula.getDsNome(), aula.getDsYouTube()))
                .collect(Collectors.toList());
        return new CursoDTO(curso.getId(), curso.getDsNome(), curso.getFgCategory().getValue(), aulaDTOS);
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        };

        curso.setDsNome(cursoDTO.dsNome());
        curso.setFgCategory(convertCategoryValue(cursoDTO.nmCategory()));

        List<Aula> aulas = cursoDTO.aulas().stream().map(aulaDTO -> {
            var aula = new Aula();
            aula.setId(aulaDTO.id());
            aula.setDsNome(aulaDTO.dsNome());
            aula.setDsYouTube(aulaDTO.dsYouTube());
            aula.setCurso(curso);
            return aula;
        }).collect(Collectors.toList());
        curso.setAulas(aulas);
        return curso;
    }

    public Category convertCategoryValue(int value) {

        return switch (value) {
            case 1 -> Category.BACKEND;
            case 2 -> Category.FRONTEND;
            default -> throw  new IllegalArgumentException("Categoria inválida");
        };
     }
}
