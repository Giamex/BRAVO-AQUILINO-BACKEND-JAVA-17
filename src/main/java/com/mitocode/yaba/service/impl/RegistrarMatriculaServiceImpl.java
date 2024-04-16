package com.mitocode.yaba.service.impl;

import com.mitocode.yaba.model.RegistrarMatricula
;
import com.mitocode.yaba.repo.ICursoRepo;
import com.mitocode.yaba.repo.IGenericRepo;
import com.mitocode.yaba.repo.IRegistrarMatriculaRepo;
import com.mitocode.yaba.service.ICursoService;
import com.mitocode.yaba.service.IRegistrarMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarMatriculaServiceImpl extends CRUDImpl<RegistrarMatricula, Integer>implements IRegistrarMatriculaService {


    //@Autowired
    private final IRegistrarMatriculaRepo repo;


    @Override
    protected IGenericRepo<RegistrarMatricula, Integer> getRepo() {
        return repo;
    }
}
