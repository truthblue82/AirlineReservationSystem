import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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
  ) { }

  getAllAirports():Airport[]|undefined {
    this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/airport`,
      {observe: 'response'})
      .subscribe((result) => {
        console.log('result airporst get', result);
        if(result.status === 200 && result.ok === true) {
          this.airPorts = result.body as Airport[];
        } else {
          this.toastr.error("Something went wrong 1!", 'Error');
        }
      },
      (error) => {
        console.log('error', error);
        this.toastr.error("Something went wrong 2!", 'Error');
      });
      return this.airPorts;
  }
}
