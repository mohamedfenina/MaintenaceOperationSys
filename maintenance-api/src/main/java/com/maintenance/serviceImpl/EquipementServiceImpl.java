package com.maintenance.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maintenance.dto.EquipementDTO;
import com.maintenance.model.Equipement;
import com.maintenance.repository.EquipementRepository;
import com.maintenance.service.EquipementService;

@Service
public class EquipementServiceImpl implements EquipementService {

	@Autowired
	private EquipementRepository equipementRepository;

	@Autowired
	private ModelMapper modelMapper;

	private EquipementDTO convertToDTO(Equipement equipement) {
		return modelMapper.map(equipement, EquipementDTO.class);
	}

	private Equipement convertToEntity(EquipementDTO equipementDTO) {
		return modelMapper.map(equipementDTO, Equipement.class);
	}

	@Override
	public List<EquipementDTO> getAllEquipements() {
		List<Equipement> equipements = equipementRepository.findAll();
		return equipements.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public EquipementDTO getEquipementById(Long id) {
		Optional<Equipement> equipement = equipementRepository.findById(id);
		return equipement.map(this::convertToDTO).orElse(null);
	}

	@Override
	public EquipementDTO saveEquipement(EquipementDTO equipementDTO) {
		Equipement equipement = convertToEntity(equipementDTO);
		Equipement saved = equipementRepository.save(equipement);
		return convertToDTO(saved);
	}

	@Override
	public EquipementDTO updateEquipement(Long id, EquipementDTO equipementDTO) {
		Optional<Equipement> existing = equipementRepository.findById(id);
		if (existing.isPresent()) {
			Equipement updated = convertToEntity(equipementDTO);
			updated.setId(id);
			Equipement saved = equipementRepository.save(updated);
			return convertToDTO(saved);
		}
		return null;
	}

	@Override
	public void deleteEquipement(Long id) {
		equipementRepository.deleteById(id);
	}
}
