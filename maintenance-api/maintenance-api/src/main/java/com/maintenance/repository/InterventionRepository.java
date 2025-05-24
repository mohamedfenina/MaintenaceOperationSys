package com.maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maintenance.model.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

	long countByStatut(String statut);

	@Query("SELECT AVG(i.cout) FROM Intervention i")
	Double findAverageCost();

	@Query("SELECT i.technicien.nom, COUNT(i) FROM Intervention i GROUP BY i.technicien.nom")
	List<Object[]> countByTechnician();

	@Query("SELECT i.equipement.nom FROM Intervention i GROUP BY i.equipement.nom ORDER BY COUNT(i) DESC LIMIT 1")
	String findMostFrequentEquipement();
}