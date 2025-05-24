import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Equipement } from 'src/app/entities/equipement.entity';
import { Panne } from 'src/app/entities/panne.entity';
import { EquipementService } from 'src/app/services/equipement/equipement.service';
import { PanneService } from 'src/app/services/panneService/panne.service';

@Component({
  selector: 'app-pannes',
  templateUrl: './pannes.component.html',
  styleUrls: ['./pannes.component.scss']
})
export class PannesComponent implements OnInit {
  displayedColumns: string[] = ['id', 'description', 'category', 'date_signalement', 'actions'];
  pannes: Panne[] = [];
  panneForm!: FormGroup; 
  equipements : Equipement[] = [];
  isEditMode: boolean = false;
  selectedPanneId: number | null = null;
  constructor(public modalService: NgbModal,private panneService: PanneService, private formBuilder: FormBuilder,private equipementService: EquipementService){}

  ngOnInit(): void {
    // this.panneForm = this.formBuilder.group({});
    this.equipementService.getEquipements().subscribe((data) => {
      this.equipements = data;
    });
    this.getPannes();
  }

    openModal(content: any, panne?: Panne) {
    this.isEditMode = !!panne;
    this.selectedPanneId = panne?.id ?? null;

    this.panneForm = this.formBuilder.group({
      description: [panne?.description || '', Validators.required],
      category: [panne?.categorie || '', Validators.required],
      dateSignalement: [panne?.dateSignalement ? this.formatDate(panne.dateSignalement) : '', Validators.required],
      equipement: [panne?.equipementId || null, Validators.required]
    });

    this.modalService.open(content, { size: 'lg' });
  }
  formatDate(date: string | Date): string {
    const d = new Date(date);
    return d.toISOString().substring(0, 10);
  }


  deletePanne(id: number) {
      this.panneService.deletePanne(id).subscribe(() => {
        this.getPannes();
      });
  }
  getPannes() {
    this.panneService.getPannes().subscribe((data) => {
      this.pannes = data;
    });
  }

    onSubmit() {
    if (this.panneForm.invalid) return;

    const formValue = this.panneForm.value;
    const pannePayload: Panne = {
      id: this.selectedPanneId || null,
      description: formValue.description,
      categorie: formValue.category,
      equipementId: formValue.equipement,
      dateSignalement: new Date(formValue.dateSignalement)
    };

    if (this.isEditMode && this.selectedPanneId) {
      this.panneService.updatePanne(pannePayload).subscribe(() => {
        this.getPannes();
        this.modalService.dismissAll();
      });
    } else {
      this.panneService.addPanne(pannePayload).subscribe(() => {
        this.getPannes();
        this.modalService.dismissAll();
      });
    }
  }
}
