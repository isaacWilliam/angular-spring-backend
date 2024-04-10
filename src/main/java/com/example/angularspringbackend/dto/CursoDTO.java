package com.example.angularspringbackend.dto;

import com.example.angularspringbackend.model.Aula;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CursoDTO(
        @Id Long id,
        @NotBlank @NotNull @Length(min = 4, max = 100) String dsNome,
        @NotNull int nmCategory,
        @NotNull @NotEmpty @Valid List<AulaDTO> aulas) {
}
