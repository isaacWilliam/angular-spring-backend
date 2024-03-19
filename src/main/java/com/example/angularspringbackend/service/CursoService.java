package com.example.angularspringbackend.service;

import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.exception.RecordNotFoundException;
import com.example.angularspringbackend.mapper.CursoMapper;
import com.example.angularspringbackend.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO record) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(record)));
    }

    public  CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO curso) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setDsNome(curso.dsNome());
                    recordFound.setFgCategory(cursoMapper.convertCategoryValue(curso.nmCategory()));
                    return cursoMapper.toDTO(cursoRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
