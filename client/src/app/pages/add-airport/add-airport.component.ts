import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AirportService } from 'src/app/services/airport.service';
import { Airport } from 'src/app/models/airport';

@Component({
  selector: 'app-add-airport',
  templateUrl: './add-airport.component.html',
  styleUrls: ['./add-airport.component.scss']
})
export class AddAirportComponent implements OnInit {
  displayModal: boolean = false;
  airport: Airport;

  constructor(
    private appTitle: Title,
    private airportSvc: AirportService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Add New Airport');
    this.airport = {
      name: '', code: '', location: ''
    };
  }

  ngOnInit(): void {
    //
  }

  onSubmit(): void {
    //
  }
}
