package com.example.angularspringbackend.dto;

import java.util.List;

public record CursoPageDTO(List<CursoDTO> cursos, int page, int size, long totalElements, int totalPages) {
}
