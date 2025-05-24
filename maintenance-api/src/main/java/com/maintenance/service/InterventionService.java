package com.maintenance.service;

import java.util.List;

import com.maintenance.dto.InterventionDTO;

public interface InterventionService {
    List<InterventionDTO> getAllInterventions();
    InterventionDTO getInterventionById(Long id);
    InterventionDTO saveIntervention(InterventionDTO intervention);
    InterventionDTO updateIntervention(Long id, InterventionDTO intervention);
    void deleteIntervention(Long id);
}