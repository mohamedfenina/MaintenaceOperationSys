package com.maintenance.dto;

import java.time.LocalDate;

public class EquipementDTO {

	  private Long id;
	  private String nom;
	  private String etat;
	  private LocalDate dateAcquisition;
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public LocalDate getDateAcquisition() {
		return dateAcquisition;
	}
	public void setDateAcquisition(LocalDate dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}
}
