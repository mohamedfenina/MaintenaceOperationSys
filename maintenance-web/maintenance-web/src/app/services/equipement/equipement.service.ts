import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Equipement } from 'src/app/entities/equipement.entity';
import { environement } from 'src/app/environement';

@Injectable({
  providedIn: 'root'
})
export class EquipementService {

  constructor(private http: HttpClient) { }

  getEquipements(): Observable<Equipement[]> {
    return this.http.get<Equipement[]>(environement.apiUrl + '/equipements/get-all');
  }

    createEquipement(panne: Equipement): Observable<Equipement> {
      return this.http.post<Equipement>(environement.apiUrl + '/equipements/create', panne);
    }
  
    updateEquipement(panne: Equipement): Observable<Equipement> {
      return this.http.put<Equipement>(`${environement.apiUrl + '/equipements'}/${panne.id}`, panne);
    }
  
    deleteEquipement(id: number): Observable<void> {
      return this.http.delete<void>(`${environement.apiUrl + '/equipements'}/${id}`);
    }
}
