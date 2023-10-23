import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {
  displayModal: boolean = false;
  constructor(
    private appTitle:Title,
    private router:Router,
    private toastr:ToastrService
  ){
    this.appTitle.setTitle('Airport Reservation System - Flight Manangement');
  }

  ngOnInit(): void {
    //
  }

  goToAddFlight():void {
    this.router.navigate(['/add-flight']);
  }
}
