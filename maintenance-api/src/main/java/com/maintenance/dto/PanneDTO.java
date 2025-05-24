package com.maintenance.dto;

import java.time.LocalDate;

public class PanneDTO {

    private Long id;
    private String description;
    private String categorie;
    private Long equipementId;
    private LocalDate dateSignalement;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Long getEquipementId() {
		return equipementId;
	}
	public void setEquipementId(Long equipementId) {
		this.equipementId = equipementId;
	}
	public LocalDate getDateSignalement() {
		return dateSignalement;
	}
	public void setDateSignalement(LocalDate dateSignalement) {
		this.dateSignalement = dateSignalement;
	}
}
