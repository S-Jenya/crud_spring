package com.stp.crud.service;

import com.stp.crud.model.Card;
import com.stp.crud.model.Institution;
import com.stp.crud.repository.InstitutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public Institution findById(Long id){
        return institutionRepository.getOne(id);
    }

    public List<Institution> findAll(){
        return institutionRepository.findAll();
    }

    public List<Institution> selectInstFromCard(Long id){
        return institutionRepository.findInstFromCardCustomQuery(id);
    }
}
