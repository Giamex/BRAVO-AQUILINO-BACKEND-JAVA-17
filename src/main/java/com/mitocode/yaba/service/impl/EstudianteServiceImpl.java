package com.mitocode.yaba.service.impl;

import com.mitocode.yaba.model.Estudiante;
import com.mitocode.yaba.repo.IEstudianteRepo;
import com.mitocode.yaba.repo.IGenericRepo;
import com.mitocode.yaba.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {

    //@Autowired
    private final IEstudianteRepo repo;


    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }
}
