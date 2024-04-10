package com.example.angularspringbackend.model;

import com.example.angularspringbackend.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity

@SQLDelete(sql = "UPDATE Curso set fg_status = 'F' WHERE id = ?")
@Where(clause = "fg_status = 'T'")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(name = "ds_nome", length = 100, nullable = false)
    private String dsNome;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_category", nullable = false)
    private Category fgCategory;

    @NotNull
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_status")
    private Boolean fgStatus = true;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "curso")
//    @JoinColumn(name = "curso_id")
    private List<Aula> aulas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getFgCategory() {
        return fgCategory;
    }

    public void setFgCategory(Category fgCategory) {
        this.fgCategory = fgCategory;
    }

    public Boolean getFgStatus() {
        return fgStatus;
    }

    public void setFgStatus(Boolean fgStatus) {
        this.fgStatus = fgStatus;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }
}
