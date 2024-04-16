package com.mitocode.yaba.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mitocode.yaba.dto.CursoDTO;
import com.mitocode.yaba.dto.EstudianteDTO;
import com.mitocode.yaba.dto.GenericResponse;
import com.mitocode.yaba.model.Curso;
import com.mitocode.yaba.model.Estudiante;
import com.mitocode.yaba.service.impl.EstudianteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

    //@Autowired
    private final EstudianteServiceImpl service;
    @Qualifier("estudianteMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>>  readAll() throws Exception {
        List<EstudianteDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }
@GetMapping("/orden")
public ResponseEntity<List<EstudianteDTO>>  orderByAge() throws Exception {
    List<EstudianteDTO> list = service.readAll().stream().map(this::convertToDto).sorted(Comparator.comparing(EstudianteDTO::getEdad).reversed()).collect(Collectors.toList());

    return ResponseEntity.ok(list);
    //return new ResponseEntity<>(list, HttpStatus.OK);
}
  /*  @GetMapping("/curso/{id}")
    public ResponseEntity<List<EstudianteDTO>>  readAllByCurso(@PathVariable("id") Integer id) throws Exception {
        List<EstudianteDTO> list = service.readAllByCurso(id).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);}
*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDTO>> readById(@PathVariable("id") Integer id) throws Exception {
        Estudiante obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse<>(200,"OK", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO dto) throws Exception {
        Estudiante obj= service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return ResponseEntity.created();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody Estudiante dto, @PathVariable("id") Integer id) throws Exception {
        Estudiante obj = service.update(dto, id);

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
    public EstudianteModel getEstudianteSimple() {
        //service= new EstudianteService();
        return service.validarEstudiante(new EstudianteModel(1,"Yessica Anggie", "Bravo Aquilino","46554600",28));


    }*/
    private EstudianteDTO convertToDto(Estudiante obj) {
        return modelMapper.map(obj, EstudianteDTO.class);
    }
    private Estudiante convertToEntity(EstudianteDTO dto) {
        return modelMapper.map(dto, Estudiante.class);
    }

}
