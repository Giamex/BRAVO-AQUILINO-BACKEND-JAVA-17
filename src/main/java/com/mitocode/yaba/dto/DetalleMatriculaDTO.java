package com.mitocode.yaba.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DetalleMatriculaDTO {

    @JsonBackReference
    private RegistrarMatriculaDTO registrarMatricula;

    @NotNull
    private CursoDTO curso;

    @NotNull
    @Size(min=3 , max=100)
    private String aula;
}
