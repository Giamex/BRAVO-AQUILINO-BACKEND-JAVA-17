package com.mitocode.yaba.controller;

import com.mitocode.yaba.dto.CursoDTO;
import com.mitocode.yaba.dto.DetalleMatriculaDTO;
import com.mitocode.yaba.dto.RegistrarMatriculaDTO
;
import com.mitocode.yaba.dto.GenericResponse;
import com.mitocode.yaba.model.RegistrarMatricula
;
import com.mitocode.yaba.service.impl.RegistrarMatriculaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/registrarMatricula")
@RequiredArgsConstructor
public class RegistrarController {

    private final RegistrarMatriculaServiceImpl service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RegistrarMatriculaDTO>> readAll() throws Exception {

       // ModelMapper modelMapper =new ModelMapper();
        List<RegistrarMatriculaDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    /* @GetMapping("/{id}")
    public ResponseEntity<RegistrarMatriculaDTO
> readById(@PathVariable("id") Integer id) throws Exception {
        RegistrarMatricula
 obj = service.readById(id);

        return ResponseEntity.ok(convertToDto(obj));
    }*/

    @GetMapping("/relaciones")
    public ResponseEntity<Map<String, List<String>>>getCursosYEstudiantes() throws Exception{
        Map<String, List<String>> cursosYEstudiantes = new HashMap<>();

        List<RegistrarMatriculaDTO> registros= service.readAll().stream().map(this::convertToDto).toList();

        for(RegistrarMatriculaDTO registro : registros){
            List<DetalleMatriculaDTO> detalles = registro.getDetalle();
            for(DetalleMatriculaDTO detalle : detalles){
                CursoDTO curso = detalle.getCurso();
                String nombreCurso = curso.getNombreCurso();

                String fullName = registro.getEstudiante().getNombreEstudiante() + " " + registro.getEstudiante().getApellidoEstudiante();

                cursosYEstudiantes.computeIfAbsent(nombreCurso, k -> new ArrayList<>()).add(fullName);
            }
        }

        return ResponseEntity.ok(cursosYEstudiantes);
    }

     @GetMapping("/{id}")
       public ResponseEntity<GenericResponse<RegistrarMatriculaDTO>> readById(@PathVariable("id") Integer id) throws Exception {
         RegistrarMatricula obj = service.readById(id);
         return ResponseEntity.ok(new GenericResponse<>(200, "OK", Arrays.asList(convertToDto(obj))));
     }

    @PostMapping
    public ResponseEntity<RegistrarMatriculaDTO> save(@Valid @RequestBody RegistrarMatriculaDTO dto) throws Exception {
        RegistrarMatricula  obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return ResponseEntity.created();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrarMatriculaDTO > update(@Valid @RequestBody RegistrarMatriculaDTO  dto, @PathVariable("id") Integer id) throws Exception {
        RegistrarMatricula obj = service.update(convertToEntity(dto), id);
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
    public RegistrarMatricula
 getCursoSimple(){

        return service. validarCurso(new RegistrarMatricula
(1,"Programacion", "PROG",true));
    }
*/

/////////////////////////////////////////////
    private RegistrarMatriculaDTO convertToDto(RegistrarMatricula obj) {
        return modelMapper.map(obj, RegistrarMatriculaDTO.class);
    }
    private RegistrarMatricula convertToEntity(RegistrarMatriculaDTO dto) {
        return modelMapper.map(dto, RegistrarMatricula.class);
    }

}
