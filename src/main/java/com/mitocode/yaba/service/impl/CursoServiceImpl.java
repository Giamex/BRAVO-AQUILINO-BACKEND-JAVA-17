package com.mitocode.yaba.service.impl;

import com.mitocode.yaba.model.Curso;
import com.mitocode.yaba.repo.ICursoRepo;
import com.mitocode.yaba.repo.IGenericRepo;
import com.mitocode.yaba.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer>implements ICursoService {


    //@Autowired
    private final ICursoRepo repo;


    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}
