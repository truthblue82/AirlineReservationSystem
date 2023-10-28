import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ScheduleFlightFlightService } from '../../services/scheduleFlight.service';
import { ScheduledFlightSearch } from 'src/app/models/scheduledFlightSearch';
import { ScheduledFlight, ScheduledFlights } from '../../models/scheduledFlight';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {
  displayModal: boolean = false;
  scheduledFlights: ScheduledFlight[];

  flightflightno: string = '';
  origin: string = '';
  destination: string = '';
  duration:string = '';
  departureDate: string = '';
  departureTime: string = '';
  returnDate: string = '';
  returnTime: string = '';
  seats: number = 0;
  ticketPrice: number = 0;

  constructor(
    private appTitle: Title,
    private router: Router,
    private scheduleFlightFlightSvc: ScheduleFlightFlightService,
    private toastr: ToastrService
  ){
    this.appTitle.setTitle('Airport Reservation System - Schedule Management');
    this.scheduledFlights = [];
  }

  ngOnInit(): void {
    this.scheduleFlightFlightSvc.getAllScheduleFlights()
    .subscribe((value: ScheduledFlights) => this.scheduledFlights = value.scheduledFlights);
  }

  handleFormSubmit(event: Event):void {
    //
  }
  goToAddSchedual():void {
    this.router.navigate(['/add-schedule']);
  }
}
