package com.maintenance.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maintenance.dto.PanneDTO;
import com.maintenance.model.Panne;
import com.maintenance.repository.PanneRepository;
import com.maintenance.service.PanneService;

@Service
public class PanneServiceImpl implements PanneService {

	  @Autowired
	  private PanneRepository panneRepository;

	  private ModelMapper modelMapper = new ModelMapper();
	  
	   private PanneDTO convertToDTO(Panne panne) {
	        return modelMapper.map(panne, PanneDTO.class);
	    }
	   
	  @Override
	  public List<PanneDTO> getAllPannes() {
	        List<Panne> pannes = panneRepository.findAll();
	        List<PanneDTO> panneDTOs = pannes.stream().map(this::convertToDTO)
	        		.collect(Collectors.toList());
	        return panneDTOs;
	    }
	  @Override
	  public PanneDTO getPanneById(Long id) {
	      Optional<Panne> panne = panneRepository.findById(id);
	      return panne.map(this::convertToDTO).orElse(null);
	  }


	    @Override
	    public PanneDTO savePanne(PanneDTO panneDTO) {
	    	
	    	Panne panne = modelMapper.map(panneDTO, Panne.class);
	        Panne savedPanne = panneRepository.save(panne);
	        return convertToDTO(savedPanne);
	    }

	    @Override
	    public PanneDTO updatePanne(Long id, PanneDTO panneDTO) {
	    	Panne panne = modelMapper.map(panneDTO, Panne.class);

	        panne.setId(id);
	        Panne updatedPanne = panneRepository.save(panne);
	        return convertToDTO(updatedPanne);
	    }

	    @Override
	    public void deletePanne(Long id) {
	        panneRepository.deleteById(id);
	    }
	  
}
