package com.example.angularspringbackend.controller;

import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.repository.CursoRepository;
import com.example.angularspringbackend.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Curso> list() {
        return cursoService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody @Valid Curso record) {
        return cursoService.create(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable @NotNull @Positive Long id) {
        return cursoService.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Curso> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Curso curso) {
        return cursoService.update(id, curso)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull @Positive Long id) {
        if (cursoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
