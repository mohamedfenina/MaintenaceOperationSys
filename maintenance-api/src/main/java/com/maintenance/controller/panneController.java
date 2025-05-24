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

import com.maintenance.dto.PanneDTO;
import com.maintenance.service.PanneService;

@RestController
@RequestMapping("/pannes")
public class panneController {

	@Autowired
	private PanneService panneService;

	@GetMapping("/get-all")
	public List<PanneDTO> getAllPannes() {
		return panneService.getAllPannes();
	}

	@GetMapping("/{id}")
	public PanneDTO getPanneById(@PathVariable Long id) {

		return panneService.getPanneById(id);

	}

	@PostMapping("/create")
	public PanneDTO createPanne(@RequestBody PanneDTO panneDTO) {

		return panneService.savePanne(panneDTO);
	}

	@PutMapping("/{id}")
	public PanneDTO updatePanne(@PathVariable Long id, @RequestBody PanneDTO panneDTO) {
		return panneService.updatePanne(id, panneDTO);
	}

	@DeleteMapping("/{id}")
	public void deletePanne(@PathVariable Long id) {
		panneService.deletePanne(id);
	}
}
