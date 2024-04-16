package com.mitocode.yaba.config;

import com.mitocode.yaba.dto.CursoDTO;
import com.mitocode.yaba.dto.EstudianteDTO;
import com.mitocode.yaba.model.Curso;
import com.mitocode.yaba.model.Estudiante;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("estudianteMapper")
    public ModelMapper estudianteMapper(){
        ModelMapper mapper = new ModelMapper();

        //Lectura
        TypeMap<Estudiante, EstudianteDTO> typeMap1 = mapper.createTypeMap(Estudiante.class ,EstudianteDTO.class);
        typeMap1.addMapping(Estudiante::getNombre, (dest, v) -> dest.setNombreEstudiante((String)v));

        //Excritura
        TypeMap<EstudianteDTO, Estudiante> typeMap2 = mapper.createTypeMap(EstudianteDTO.class, Estudiante.class);
        typeMap2.addMapping(EstudianteDTO::getNombreEstudiante, (dest, v) -> dest.setNombre((String)v));

        return mapper;
    }
    @Bean("cursoMapper")
    public ModelMapper cursoMapper(){
        ModelMapper mapper = new ModelMapper();

        //Lectura
        TypeMap<Curso, CursoDTO> typeMap1 = mapper.createTypeMap(Curso.class ,CursoDTO.class);
        typeMap1.addMapping(Curso::getNombre, (dest, v) -> dest.setNombreCurso((String)v));

        //Excritura
        TypeMap<CursoDTO, Curso> typeMap2 = mapper.createTypeMap(CursoDTO.class, Curso.class);
        typeMap2.addMapping(CursoDTO::getNombreCurso, (dest, v) -> dest.setNombre((String)v));

        return mapper;
    }

    @Bean("defaultMapper")
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
