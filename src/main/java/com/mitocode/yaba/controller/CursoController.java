package com.mitocode.yaba.controller;

import java.util.Arrays;
import java.util.List;

import com.mitocode.yaba.dto.CursoDTO;
import com.mitocode.yaba.dto.GenericResponse;
import com.mitocode.yaba.model.Curso;
import com.mitocode.yaba.service.impl.CursoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor

public class CursoController {

    private final CursoServiceImpl service;
    @Qualifier("cursoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll() throws Exception {

       // ModelMapper modelMapper =new ModelMapper();
        List<CursoDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    /* @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Curso obj = service.readById(id);

        return ResponseEntity.ok(convertToDto(obj));
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CursoDTO>> readById(@PathVariable("id") Integer id) throws Exception {
        Curso obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse<>(200,"OK", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO dto) throws Exception {
        Curso obj= service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoDTO dto, @PathVariable("id") Integer id) throws Exception {
        Curso obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    @GetMapping
    public Curso getCursoSimple(){

        return service. validarCurso(new Curso(1,"Programacion", "PROG",true));
    }
*/

/////////////////////////////////////////////
    private CursoDTO convertToDto(Curso obj) {

        return modelMapper.map(obj, CursoDTO.class);
    }
    private Curso convertToEntity(CursoDTO dto) {

        return modelMapper.map(dto, Curso.class);
    }

}
