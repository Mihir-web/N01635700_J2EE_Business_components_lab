package com.week3.MVC.service;


import com.week3.MVC.entity.Program;
import com.week3.MVC.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
}

