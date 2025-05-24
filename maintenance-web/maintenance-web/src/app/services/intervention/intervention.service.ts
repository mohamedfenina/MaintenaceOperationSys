import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Intervention } from 'src/app/entities/intervention.entity';
import { environement } from 'src/app/environement';

@Injectable({
  providedIn: 'root'
})
export class InterventionService {

  constructor(private http: HttpClient) { }
    private baseUrl = environement.apiUrl + '/interventions';

    getAll(): Observable<Intervention[]> {
    return this.http.get<Intervention[]>(`${this.baseUrl}/get-all`);
  }

  create(intervention: Intervention): Observable<Intervention> {
    return this.http.post<Intervention>(`${this.baseUrl}/create`, intervention);
  }

  getById(id: number): Observable<Intervention> {
    return this.http.get<Intervention>(`${this.baseUrl}/${id}`);
  }

  update(id: number, intervention: Intervention): Observable<Intervention> {
    return this.http.put<Intervention>(`${this.baseUrl}/${id}`, intervention);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
}
