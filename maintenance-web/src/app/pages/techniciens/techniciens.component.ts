import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Technicien } from 'src/app/entities/technicien.entity';
import { TechnicienService } from 'src/app/services/technicien/technicien.service';

@Component({
  selector: 'app-techniciens',
  templateUrl: './techniciens.component.html',
  styleUrls: ['./techniciens.component.scss']
})
export class TechniciensComponent implements OnInit {
  techniciens: Technicien[] = [];
  displayedColumns: string[] = ['id', 'nom', 'competences', 'disponibilite', 'actions'];
  technicienForm!: FormGroup;
  isEditMode = false;
  currentTechnicienId: number | null = null;

  constructor(
    private technicienService: TechnicienService,
    private formBuilder: FormBuilder,
    public modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadTechniciens();
  }

  loadTechniciens(): void {
    this.technicienService.getAll().subscribe(data => this.techniciens = data);
  }
   openModal(content: any, technicien?: Technicien): void {
    if (technicien) {
      this.isEditMode = true;
      this.currentTechnicienId = technicien.id;
      this.technicienForm = this.formBuilder.group({
        nom: [technicien.nom, Validators.required],
        competences: [technicien.competences, Validators.required],
        disponibilite: [technicien.disponibilite]
      });
    } else {
      this.isEditMode = false;
      this.currentTechnicienId = null;
      this.technicienForm = this.formBuilder.group({
        nom: ['', Validators.required],
        competences: ['', Validators.required],
        disponibilite: [false]
      });
    }

    this.modalService.open(content, { size: 'lg' });
  }

  onSubmit(): void {
    if (this.technicienForm.valid) {
      const formValues = this.technicienForm.value;

      const technicien: Technicien = {
        id: this.currentTechnicienId ?? null,
        nom: formValues.nom,
        competences: formValues.competences,
        disponibilite: formValues.disponibilite,
        interventions: [] // can be ignored if not needed here
      };

      if (this.isEditMode && this.currentTechnicienId !== null) {
        this.technicienService.update(this.currentTechnicienId, technicien).subscribe(() => {
          this.loadTechniciens();
          this.modalService.dismissAll();
        });
      } else {
        this.technicienService.create(technicien).subscribe(() => {
          this.loadTechniciens();
          this.modalService.dismissAll();
        });
      }
    }
  }

  deleteTechnicien(id: number): void {
    this.technicienService.delete(id).subscribe(() => {
      this.loadTechniciens();
    });
  }
}
