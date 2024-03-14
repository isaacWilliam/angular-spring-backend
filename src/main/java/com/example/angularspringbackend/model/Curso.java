package com.example.angularspringbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ds_nome", length = 200, nullable = false)
    private String dsNome;

    @Column(name = "ds_category", length = 10, nullable = false)
    private String dsCategory;
}
