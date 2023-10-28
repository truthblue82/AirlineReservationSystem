import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, map } from 'rxjs';
import { Airport } from 'src/app/models/airport';
import { environment } from 'src/environments/environment';
import { ScheduledFlightSearch } from '../models/scheduledFlightSearch';
import { ScheduledFlight } from '../models/scheduledFlight';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  scheduledFlights: ScheduledFlight[] | undefined;
  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) {

  }

  findScheduledFlights(criteria: ScheduledFlightSearch): Observable<any> {
    return this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/scheduled-flight/search?deptAirport=${criteria.departureAirportCode}&arrAirport=${criteria.arrivalAirportCode}&deptDate=${criteria.departureDate}&noOfPassengers=${criteria.noOfPassengers}`);
  }
}


