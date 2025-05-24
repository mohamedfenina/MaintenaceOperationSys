package com.maintenance.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maintenance.dto.TechnicienDTO;
import com.maintenance.model.Technicien;
import com.maintenance.repository.TechnicienRepository;
import com.maintenance.service.TechnicienService;

@Service
public class TechnicienServiceImpl implements TechnicienService {
	
    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private ModelMapper modelMapper;

    private TechnicienDTO convertToDTO(Technicien technicien) {
        return modelMapper.map(technicien, TechnicienDTO.class);
    }
    
    private Technicien convertToEntity(TechnicienDTO technicienDTO) {
        return modelMapper.map(technicienDTO, Technicien.class);
    }

    @Override
    public List<TechnicienDTO> getAllTechniciens() {
        List<Technicien> techniciens = technicienRepository.findAll();
        return techniciens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicienDTO getTechnicienById(Long id) {
        Optional<Technicien> technicien = technicienRepository.findById(id);
        return technicien.map(this::convertToDTO).orElse(null);
    }

    @Override
    public TechnicienDTO saveTechnicien(TechnicienDTO technicienDTO) {
        Technicien technicien = convertToEntity(technicienDTO);
        Technicien saved = technicienRepository.save(technicien);
        return convertToDTO(saved);
    }

    @Override
    public TechnicienDTO updateTechnicien(Long id, TechnicienDTO technicienDTO) {
        Optional<Technicien> existing = technicienRepository.findById(id);
        if (existing.isPresent()) {
            Technicien updated = convertToEntity(technicienDTO);
            updated.setId(id);
            Technicien saved = technicienRepository.save(updated);
            return convertToDTO(saved);
        }
        return null;
    }

    @Override
    public void deleteTechnicien(Long id) {
        technicienRepository.deleteById(id);
    }
}
