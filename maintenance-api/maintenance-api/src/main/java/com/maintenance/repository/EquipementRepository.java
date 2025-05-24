package com.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maintenance.model.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}