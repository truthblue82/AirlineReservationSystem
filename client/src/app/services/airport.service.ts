import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, map } from 'rxjs';
import { Airport } from 'src/app/models/airport';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AirportService {
  airPorts?: Airport[];

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.airPorts = [];
  }

  getAllAirports(): Observable<any> {
    return this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/airport`);
  }


}
