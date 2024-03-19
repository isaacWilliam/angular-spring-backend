package com.example.angularspringbackend.controller;

import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/cursos")

public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> list() {
        return cursoService.list();
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
