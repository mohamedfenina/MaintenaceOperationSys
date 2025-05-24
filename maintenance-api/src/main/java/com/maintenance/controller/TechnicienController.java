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

import com.maintenance.dto.TechnicienDTO;
import com.maintenance.service.TechnicienService;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController {


    @Autowired
    private TechnicienService technicienService;

    @GetMapping("/get-all")
    public List<TechnicienDTO> getAllTechniciens() {
        return technicienService.getAllTechniciens();
    }

    @GetMapping("/{id}")
    public TechnicienDTO getTechnicienById(@PathVariable Long id) {
        return technicienService.getTechnicienById(id);
    }

    @PostMapping("/create")
    public TechnicienDTO saveTechnicien(@RequestBody TechnicienDTO technicien) {
        return technicienService.saveTechnicien(technicien);
    }

    @PutMapping("/{id}")
    public TechnicienDTO updateTechnicien(@PathVariable Long id, @RequestBody TechnicienDTO technicien) {
        return technicienService.updateTechnicien(id, technicien);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
    }
}
