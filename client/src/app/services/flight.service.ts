import { Injectable } from '@angular/core';
import { Flight } from '../models/flight';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  flights?: Flight[];

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.flights = [];
  }

  getAllFlights(): Observable<any> {
    return this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/flight/allFlight`);
  }

  addFlight(flight: Flight): Observable<any> {
    return this.http.post(
      `${environment.GATEWAY_BASE_URL}/api/flight/addFlight`,
      flight
    );
  }

  updateFlight(flight: Flight): Observable<any> {
    return this.http.put(
      `${environment.GATEWAY_BASE_URL}/api/flight/updateFlight`,
      flight
    )
  }

  deleteFlight(flightNo: string): Observable<any> {
    return this.http.delete(
      `${environment.GATEWAY_BASE_URL}/api/flight/deleteFlight/${flightNo}`);
  }
}
