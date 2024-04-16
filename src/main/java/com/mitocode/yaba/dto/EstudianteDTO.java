package com.mitocode.yaba.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {
    private Integer idEstudiante;

    @NotNull
    @Size(min=3, max=250)
    private String nombreEstudiante;

    @NotNull
    @Size(min=3, max=250)
    private String apellidoEstudiante;

    @NotNull
    @Size(min=8, max=8)
    private String dni;

    @NotNull
    @Max(value=100)
    @Min(value=1)
    private Integer edad;
}
