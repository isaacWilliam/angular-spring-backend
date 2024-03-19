package com.example.angularspringbackend.mapper;

import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.enums.Category;
import com.example.angularspringbackend.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getDsNome(), curso.getFgCategory().getValue());
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
