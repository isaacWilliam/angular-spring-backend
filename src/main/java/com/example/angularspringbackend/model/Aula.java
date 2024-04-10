package com.example.angularspringbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 4, max = 100)
    @Column(name = "ds_nome", length = 100, nullable = false)
    private String dsNome;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 30)
    @Column(name = "ds_you_tube", length = 100, nullable = false)
    private String dsYouTube;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Curso curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }


    public String getDsYouTube() {
        return dsYouTube;
    }

    public void setDsYouTube(String dsYouTube) {
        this.dsYouTube = dsYouTube;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


}
