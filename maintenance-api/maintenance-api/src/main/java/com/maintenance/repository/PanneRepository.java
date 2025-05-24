package com.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maintenance.model.Panne;

public interface PanneRepository extends JpaRepository<Panne, Long> {
}
