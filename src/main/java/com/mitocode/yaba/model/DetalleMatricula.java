package com.mitocode.yaba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class DetalleMatricula {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        private Integer idDetalleMatricula;

        @ManyToOne
        @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_matricula_curso"))
        private Curso curso;

        @ManyToOne
        @JoinColumn(name = "id_matricula", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_matricula_matricula"))
        private RegistrarMatricula registrarMatricula;

        @Column(length = 100, nullable = false)
        private String aula;

}
