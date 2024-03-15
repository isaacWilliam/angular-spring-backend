package com.example.angularspringbackend.service;

import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id);
    }

    public Curso create(@Valid Curso record) {
        return cursoRepository.save(record);
    }

    public  Optional<Curso> update(@NotNull @Positive Long id, @Valid Curso curso) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setDsNome(curso.getDsNome());
                    recordFound.setDsCategory(curso.getDsCategory());
                    return cursoRepository.save(recordFound);
                });
    }

    public Boolean delete(@NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    cursoRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}