package com.maintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maintenance.dto.EquipementDTO;
import com.maintenance.service.EquipementService;

@RestController
@RequestMapping("/equipements")
public class EquipementController {

	@Autowired
	private EquipementService equipementService;

	@GetMapping("/get-all")
	public List<EquipementDTO> getAllEquipements() {
		return equipementService.getAllEquipements();
	}

	@GetMapping("/{id}")
	public EquipementDTO getEquipementById(@PathVariable Long id) {
		return equipementService.getEquipementById(id);
	}

	@PostMapping("/create")
	public EquipementDTO saveEquipement(@RequestBody EquipementDTO equipement) {
		return equipementService.saveEquipement(equipement);
	}

	@PutMapping("/{id}")
	public EquipementDTO updateEquipement(@PathVariable Long id, @RequestBody EquipementDTO equipement) {
		return equipementService.updateEquipement(id, equipement);
	}

	@DeleteMapping("/{id}")
	public void deleteEquipement(@PathVariable Long id) {
		equipementService.deleteEquipement(id);
	}
}
