import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs';
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

  getAllAirports(): any {
    return this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/airport`);
      // .map((respone:HttpResponse) => respone.json())
      // .

      /*.subscribe((result) => {
        console.log('result airporst get', result);

        if(result.status === 200 && result.ok === true) {
          const body = result.body;
          this.airPorts = body?.airports;
          return this.airPorts;
        } else {
          this.toastr.error("Something went wrong 1!", 'Error');
          return this.airPorts;
        }
      },
      (error) => {
        console.log('error', error);
        this.toastr.error("Something went wrong 2!", 'Error');
        return this.airPorts;
      });*/
  }
}
