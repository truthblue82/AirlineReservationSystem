import { Injectable } from '@angular/core';
import { Flight } from '../models/flight';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  flights?: Flight[];

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) { }

  getAllFlights():Flight[]|undefined {
    this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/flight`,
      {observe: 'response'})
      .subscribe((result) => {
        console.log('result flight get', result);
        if(result.status === 200 && result.ok === true) {
          this.flights = result.body as Flight[];
        } else {
          this.toastr.error("Something went wrong 1!", 'Error');
        }
      },
      (error) => {
        console.log('error', error);
        this.toastr.error("Something went wrong 2!", 'Error');
      });
      return this.flights;
  }
}
