import { Intervention } from "./intervention.entity";

export interface Technicien {
    id: number | null;
    nom: string;
    competences: string;
    disponibilite: boolean;
    interventions: Intervention[] 
  }
  