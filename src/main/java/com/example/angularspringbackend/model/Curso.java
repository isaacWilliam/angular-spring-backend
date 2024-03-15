package com.example.angularspringbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.validator.constraints.Length;

@Data
@Entity

@SQLDelete(sql = "UPDATE Curso set fg_status = 'F' WHERE id = ?")
@Where(clause = "fg_status = 'T'")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(name = "ds_nome", length = 100, nullable = false)
    private String dsNome;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Front-end|Back-end")
    @Column(name = "ds_category", length = 10, nullable = false)
    private String dsCategory;

    @NotNull
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_status")
    private Boolean fgStatus = true;
}
