package com.team4.service.division;

import com.team4.domain.division.Division;
import com.team4.domain.division.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisionService {

    private DivisionRepository divisionRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division createDivision(Division division){
        return divisionRepository.save(division);
    }

    public List<Division> getAllDivisions(){
        List<Division> divisions = new ArrayList<>();
        divisionRepository.findAll().forEach(divisions::add);
        return divisions;
    }
}
