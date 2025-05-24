package com.maintenance.dto;

import java.time.LocalDate;

public class InterventionDTO {

	   private Long id;
	    private Long equipementId;
	    private Long technicienId;
	    private String statut;
	    private LocalDate date;
	    private Double cout;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getEquipementId() {
			return equipementId;
		}
		public void setEquipementId(Long equipementId) {
			this.equipementId = equipementId;
		}
		public Long getTechnicienId() {
			return technicienId;
		}
		public void setTechnicienId(Long technicienId) {
			this.technicienId = technicienId;
		}
		public String getStatut() {
			return statut;
		}
		public void setStatut(String statut) {
			this.statut = statut;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public Double getCout() {
			return cout;
		}
		public void setCout(Double cout) {
			this.cout = cout;
		}
	    
	    
}
