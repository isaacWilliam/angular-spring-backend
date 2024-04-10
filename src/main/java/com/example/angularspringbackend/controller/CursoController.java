package com.example.angularspringbackend.controller;

import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.dto.CursoPageDTO;
import com.example.angularspringbackend.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/cursos")

public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public CursoPageDTO list(@RequestParam(name = "page", defaultValue = "0") @PositiveOrZero int pageNumber, @RequestParam(defaultValue = "10") @Positive @Max(20) int pageSize) {
        return cursoService.list(pageNumber, pageSize);
    }
    @GetMapping("/{id}")
    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody @Valid @NotNull CursoDTO record) {
        return cursoService.create(record);
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CursoDTO curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
       cursoService.delete(id);
    }

}
