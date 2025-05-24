package com.maintenance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maintenance.dto.InterventionDTO;
import com.maintenance.repository.InterventionRepository;
import com.maintenance.repository.PanneRepository;
import com.maintenance.service.InterventionService;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

	@Autowired
	private InterventionService interventionService;

	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private PanneRepository panneRepository;

	@GetMapping("/get-all")
	public List<InterventionDTO> getAllInterventions() {
		return interventionService.getAllInterventions();
	}

	@GetMapping("/{id}")
	public InterventionDTO getInterventionById(@PathVariable Long id) {
		return interventionService.getInterventionById(id);
	}

	@PostMapping("/create")
	public InterventionDTO saveIntervention(@RequestBody InterventionDTO intervention) {
		return interventionService.saveIntervention(intervention);
	}

	@PutMapping("/{id}")
	public InterventionDTO updateIntervention(@PathVariable Long id, @RequestBody InterventionDTO intervention) {
		return interventionService.updateIntervention(id, intervention);
	}

	@DeleteMapping("/{id}")
	public void deleteIntervention(@PathVariable Long id) {
		interventionService.deleteIntervention(id);
	}

	@GetMapping("/kpis")
	public Map<String, Object> getDashboardKPIs() {
		Map<String, Object> kpis = new HashMap<>();

		kpis.put("totalInterventions", interventionRepository.count());
		kpis.put("totalPannes", panneRepository.count());
		kpis.put("ongoingInterventions", interventionRepository.countByStatut("in progress"));
		kpis.put("closedInterventions", interventionRepository.countByStatut("closed"));
		kpis.put("averageInterventionCost", interventionRepository.findAverageCost());
		kpis.put("interventionsPerTechnician", interventionRepository.countByTechnician());
		kpis.put("mostFrequentEquipement", interventionRepository.findMostFrequentEquipement());

		return kpis;
	}
}
