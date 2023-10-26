import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Airport } from 'src/app/models/airport';
import { Airports } from 'src/app/models/airports';
import { AirportService } from 'src/app/services/airport.service';

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.scss']
})
export class AirportComponent implements OnInit{
  displayModal: boolean = false;
  airPorts?: Airport[];

  constructor(
    private appTitle: Title,
    private router: Router,
    private airPortSvc: AirportService,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Airport Management');
  }

  ngOnInit(): void {
    this.airPortSvc.getAllAirports().subscribe((value: Airports) => this.airPorts = value.airports);

    console.log('airPorts', this.airPorts);
  }
  goToAddAirPort():void {
    this.router.navigate(['/add-airport']);
  }
}
