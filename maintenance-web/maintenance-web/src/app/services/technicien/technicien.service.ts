import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Technicien } from 'src/app/entities/technicien.entity';
import { environement } from 'src/app/environement';

@Injectable({
  providedIn: 'root'
})
export class TechnicienService {

  private baseUrl = environement.apiUrl + '/techniciens';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Technicien[]> {
    return this.http.get<Technicien[]>(`${this.baseUrl}/get-all`);
  }

  getById(id: number): Observable<Technicien> {
    return this.http.get<Technicien>(`${this.baseUrl}/${id}`);
  }

  create(technicien: Technicien): Observable<Technicien> {
    return this.http.post<Technicien>(`${this.baseUrl}/create`, technicien);
  }

  update(id: number | null, technicien: Technicien): Observable<Technicien> {
    return this.http.put<Technicien>(`${this.baseUrl}/${id}`, technicien);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }}
