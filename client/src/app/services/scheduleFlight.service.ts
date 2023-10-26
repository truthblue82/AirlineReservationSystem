import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ScheduledFlight } from '../models/scheduledFlight';

@Injectable({
  providedIn: 'root'
})
export class ScheduleFlightFlightService {
  scheduleeFlights?: ScheduledFlight[];

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.scheduleeFlights = [];
  }

  getAllScheduleFlights(): Observable<any> {
    return this.http.get(
      `${environment.GATEWAY_BASE_URL}/api/scheduled-flight/viewall`);
  }
}
