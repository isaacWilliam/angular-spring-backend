package com.example.angularspringbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CursoDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String dsNome,
        @NotNull int nmCategory
) {
}
