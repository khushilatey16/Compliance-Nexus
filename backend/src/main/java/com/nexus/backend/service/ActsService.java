package com.nexus.backend.service;

import com.nexus.backend.entity.Act;
import com.nexus.backend.entity.Compliance;
import com.nexus.backend.entity.preferences.Category;
import com.nexus.backend.entity.preferences.Industry;
import com.nexus.backend.entity.preferences.Ministry;
import com.nexus.backend.entity.preferences.State;
import com.nexus.backend.repository.*;
import com.nexus.backend.specification.ActSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActsService {

    @Autowired
    private ActRepository actRepository;

    @Autowired
    private ComplianceRepository complianceRepository;

    @Autowired
    private MinistryRepository ministryRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StateRepository stateRepository;

    public Act createAct(Act newAct, Integer userId) {
        newAct.setDate(LocalDateTime.now());
        newAct.setUploaderId(userId);

        Integer ministryId = newAct.getMinistry().getId();
        Integer industryId = newAct.getIndustry().getId();
        Integer categoryId = newAct.getCategory().getId();
        Integer stateId = newAct.getState().getId();

        Ministry ministry = ministryRepository.findById(ministryId).orElseThrow(() -> new EntityNotFoundException("Ministry not found with ID: " + ministryId));
        Industry industry = industryRepository.findById(industryId).orElseThrow(() -> new EntityNotFoundException("Industry not found with ID: " + industryId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + categoryId));
        State state = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException("State not found with ID: " + stateId));

        newAct.setMinistry(ministry);
        newAct.setIndustry(industry);
        newAct.setCategory(category);
        newAct.setState(state);

        newAct = actRepository.save(newAct);

        List<Compliance> complianceList = newAct.getComplianceSet();
        for (Compliance compliance : complianceList) {
            compliance.setAct(newAct);
        }

        complianceRepository.saveAll(complianceList);

        newAct.setTotalCompliance(complianceList.size());

        return actRepository.save(newAct);
    }

    public void deleteAct(Integer actId) {

        if (actRepository.existsById(actId)) {
            actRepository.deleteById(actId);
        } else {
            try {
                throw new Exception("Act not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Act> searchActs(String searchString) {
        Specification<Act> titleSpecification = ActSpecification.titleContains(searchString);

        Specification<Act> finalSpecification = Specification.where(titleSpecification);

        return actRepository.findAll(finalSpecification);
    }


}

