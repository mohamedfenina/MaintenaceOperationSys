import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Equipement } from 'src/app/entities/equipement.entity';
import { Intervention } from 'src/app/entities/intervention.entity';
import { Technicien } from 'src/app/entities/technicien.entity';
import { EquipementService } from 'src/app/services/equipement/equipement.service';
import { InterventionService } from 'src/app/services/intervention/intervention.service';
import { TechnicienService } from 'src/app/services/technicien/technicien.service';

@Component({
  selector: 'app-interventions',
  templateUrl: './interventions.component.html',
  styleUrls: ['./interventions.component.scss']
})
export class InterventionsComponent implements OnInit {
  interventions: Intervention[] = [];
  techniciens: Technicien[] = [];
  allTechniciens: Technicien[] = [];
  equipements: Equipement[] = [];

  interventionForm: FormGroup;
  isEditMode = false;
  currentInterventionId: number | null = null;

  displayedColumns: string[] = ['id', 'equipementId', 'technicienId', 'statut', 'date', 'cout', 'actions'];

  constructor(
    private interventionService: InterventionService,
    private technicienService: TechnicienService,
    private equipementService: EquipementService,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) {
    this.interventionForm = this.fb.group({
      equipementId: [null, Validators.required],
      technicienId: [null, Validators.required],
      statut: ['', Validators.required],
      date: ['', Validators.required],
      cout: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.loadInterventions();
    this.loadTechniciens();
    this.loadEquipements();
  }

  loadInterventions() {
    this.interventionService.getAll().subscribe(data => this.interventions = data);
  }

  loadTechniciens() {
    this.technicienService.getAll().subscribe(data => {
      this.allTechniciens = data;
      this.techniciens = data.filter(tech => tech.disponibilite == true)
    });
  }

  loadEquipements() {
    this.equipementService.getEquipements().subscribe(data => this.equipements = data);
  }

  openModal(content: any, intervention?: Intervention) {
    this.isEditMode = !!intervention;
    this.currentInterventionId = intervention?.id ?? null;

    if (intervention) {
      this.interventionForm.patchValue({
        equipementId: intervention.equipementId,
        technicienId: intervention.technicienId,
        statut: intervention.statut,
        date: intervention.date,
        cout: intervention.cout
      });
    } else {
      this.interventionForm.reset();
    }

    this.modalService.open(content, { size: 'lg' });
  }

  onSubmit(modal: any) {
    const intervention: Intervention = this.interventionForm.value;

    if (this.isEditMode && this.currentInterventionId) {
      this.interventionService.update(this.currentInterventionId, intervention).subscribe((res) => {
        this.updateTechnicien(res)
        this.loadInterventions();
        modal.close();
      });
    } else {
      this.interventionService.create(intervention).subscribe((res) => {
        this.updateTechnicien(res)
        this.loadInterventions();
        modal.close();
      });
    }
  }
  updateTechnicien(intervention : Intervention){
    let techn = this.allTechniciens.filter(tech => tech.id == intervention.technicienId);
    if(techn.length >0){
      techn[0].disponibilite = intervention.statut == 'closed'
      this.technicienService.update(techn[0].id,techn[0]).subscribe();
    }
  }
  deleteIntervention(id: number) {
    this.interventionService.delete(id).subscribe(() => this.loadInterventions());
  }
}
