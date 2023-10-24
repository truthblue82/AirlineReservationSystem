import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Flight } from 'src/app/models/flight';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {
  displayModal: boolean = false;
  flights?: Flight[];

  constructor(
    private appTitle:Title,
    private router:Router,
    private flightSvc: FlightService,
    private toastr:ToastrService
  ){
    this.appTitle.setTitle('Airport Reservation System - Flight Manangement');
    this.flights = [];
  }

  ngOnInit(): void {
    //this.flights = this.flightSvc.getAllFlights();
  }

  goToAddFlight():void {
    this.router.navigate(['/add-flight']);
  }
}
