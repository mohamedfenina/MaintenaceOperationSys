import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
kpis: any = {};

constructor(private http: HttpClient){
  
}
ngOnInit(): void {
  this.http.get('/api/dashboard/kpis').subscribe((data: any) => {
    this.kpis = data;
  });
}

}
