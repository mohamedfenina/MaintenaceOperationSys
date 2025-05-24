package com.maintenance.service;

import java.util.List;

import com.maintenance.dto.PanneDTO;
import com.maintenance.model.Panne;

public interface PanneService {
	    List<PanneDTO> getAllPannes();
	    PanneDTO getPanneById(Long id);
	    PanneDTO savePanne(PanneDTO panneDTO);
	    PanneDTO updatePanne(Long id, PanneDTO panneDTO);
	    void deletePanne(Long id);
}