import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Equipement } from 'src/app/entities/equipement.entity';
import { EquipementService } from 'src/app/services/equipement/equipement.service';

@Component({
  selector: 'app-equipements',
  templateUrl: './equipements.component.html',
  styleUrls: ['./equipements.component.scss']
})
export class EquipementsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'nom', 'etat', 'dateAcquisition', 'actions'];
  equipements: Equipement[] = [];
  equipementForm!: FormGroup;
  isEditMode = false;
  currentEquipementId: number | null = null;

  constructor(
    private equipementService: EquipementService,
    private formBuilder: FormBuilder,
    public modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getEquipements();
  }

openModal(content: any, equipement?: Equipement): void {
  if (equipement) {
    // Edit mode
    this.isEditMode = true;
    this.currentEquipementId = equipement.id;
    this.equipementForm = this.formBuilder.group({
      nom: [equipement.nom, Validators.required],
      etat: [equipement.etat, Validators.required],
      dateAcquisition: [this.formatDate(equipement.dateAcquisition), Validators.required]
    });
  } else {
    this.isEditMode = false;
    this.currentEquipementId = null;
    this.equipementForm = this.formBuilder.group({
      nom: ['', Validators.required],
      etat: ['', Validators.required],
      dateAcquisition: ['', Validators.required]
    });
  }

  this.modalService.open(content, { size: 'lg' });
}
formatDate(date: Date | string): string {
  const d = new Date(date);
  return d.toISOString().split('T')[0];
}


  getEquipements(): void {
    this.equipementService.getEquipements().subscribe((data) => {
      this.equipements = data;
    });
  }

onSubmit(): void {
  if (this.equipementForm.valid) {
    const formValues = this.equipementForm.value;

    const equipement: Equipement = {
      id: this.currentEquipementId ?? null,
      nom: formValues.nom,
      etat: formValues.etat,
      dateAcquisition: formValues.dateAcquisition
    };

    if (this.isEditMode && this.currentEquipementId !== null) {
      // Update
      this.equipementService.updateEquipement(equipement).subscribe(() => {
        this.getEquipements();
        this.modalService.dismissAll();
      });
    } else {
      this.equipementService.createEquipement(equipement).subscribe(() => {
        this.getEquipements();
        this.modalService.dismissAll();
      });
    }
  }
}


  editEquipement(equipement: Equipement): void {
    // TODO: implement edit logic
  }

  deleteEquipement(id: number): void {
    this.equipementService.deleteEquipement(id).subscribe(() => {
      this.getEquipements();
    });
  }
}