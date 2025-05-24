package com.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maintenance.model.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
}