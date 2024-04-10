package com.example.angularspringbackend.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AulaDTO(
        @Id Long id,
        @NotNull @NotBlank @Length(min = 4, max = 100) String dsNome,
        @NotNull @NotBlank @Length(min = 5, max = 30) String dsYouTube
) {}
