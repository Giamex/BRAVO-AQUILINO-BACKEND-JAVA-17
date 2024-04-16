package com.mitocode.yaba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private int estado;
    private String mensaje;
    private List<T> datos;
}
