package com.example.angularspringbackend.service;

import com.example.angularspringbackend.dto.CursoDTO;
import com.example.angularspringbackend.dto.CursoPageDTO;
import com.example.angularspringbackend.exception.RecordNotFoundException;
import com.example.angularspringbackend.dto.mapper.CursoMapper;
import com.example.angularspringbackend.model.Curso;
import com.example.angularspringbackend.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//    public List<CursoDTO> list() {
//        return cursoRepository.findAll().stream()
//                .map(cursoMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    public CursoPageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(20) int pageSize) {
        Page<Curso> page = cursoRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CursoDTO> cursoDTOS = page.get().map(cursoMapper::toDTO).collect(Collectors.toList());
        return new CursoPageDTO(cursoDTOS, page.getNumber(), page.getSize() ,page.getTotalElements(), page.getTotalPages());
    }

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO record) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(record)));
    }

    public  CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    Curso curso = cursoMapper.toEntity(cursoDTO);
                    recordFound.setDsNome(cursoDTO.dsNome());
                    recordFound.setFgCategory(cursoMapper.convertCategoryValue(cursoDTO.nmCategory()));
//                    recordFound.setAulas(curso.getAulas());
                    recordFound.getAulas().clear();
                    curso.getAulas().forEach(recordFound.getAulas()::add);
                    return cursoMapper.toDTO(cursoRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
