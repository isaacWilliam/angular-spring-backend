package com.example.angularspringbackend.controller;

import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cursos")

public class CursoController {

    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody Curso record) {
        return cursoRepository.save(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable  Long id) {
        return cursoRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setDsNome(curso.getDsNome());
                    recordFound.setDsCategory(curso.getDsCategory());
                    Curso update = cursoRepository.save(recordFound);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
