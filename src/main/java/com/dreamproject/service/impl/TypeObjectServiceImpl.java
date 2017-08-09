package com.dreamproject.service.impl;

import com.dreamproject.dao.TypeObjectDao;
import com.dreamproject.entity.TypeObject;
import com.dreamproject.service.TypeObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class TypeObjectServiceImpl implements TypeObjectService {

    @Autowired
    private TypeObjectDao typeObjectDao;

    public TypeObject save(TypeObject typeObject){
        return typeObjectDao.save(typeObject);
    }

    public List<TypeObject> findAll(){
        return (List<TypeObject>) typeObjectDao.findAll();
    }

    public TypeObject findByName(String name){
        return typeObjectDao.findByName(name);
    }
}
