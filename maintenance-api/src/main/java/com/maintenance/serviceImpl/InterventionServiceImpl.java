package com.maintenance.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maintenance.dto.InterventionDTO;
import com.maintenance.model.Intervention;
import com.maintenance.repository.InterventionRepository;
import com.maintenance.service.InterventionService;

@Service
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private ModelMapper modelMapper;

	private InterventionDTO convertToDTO(Intervention intervention) {
		return modelMapper.map(intervention, InterventionDTO.class);
	}

	private Intervention convertToEntity(InterventionDTO interventionDTO) {
		return modelMapper.map(interventionDTO, Intervention.class);
	}

	@Override
	public List<InterventionDTO> getAllInterventions() {
		List<Intervention> interventions = interventionRepository.findAll();
		return interventions.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public InterventionDTO getInterventionById(Long id) {
		Optional<Intervention> intervention = interventionRepository.findById(id);
		return intervention.map(this::convertToDTO).orElse(null);
	}

	@Override
	public InterventionDTO saveIntervention(InterventionDTO interventionDTO) {
		Intervention intervention = convertToEntity(interventionDTO);
		Intervention saved = interventionRepository.save(intervention);
		return convertToDTO(saved);
	}

	@Override
	public InterventionDTO updateIntervention(Long id, InterventionDTO interventionDTO) {
		Optional<Intervention> existing = interventionRepository.findById(id);
		if (existing.isPresent()) {
			Intervention updated = convertToEntity(interventionDTO);
			updated.setId(id);
			Intervention saved = interventionRepository.save(updated);
			return convertToDTO(saved);
		}
		return null;
	}

	@Override
	public void deleteIntervention(Long id) {
		interventionRepository.deleteById(id);
	}
}
