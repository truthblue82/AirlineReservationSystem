import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-flight-booking',
  templateUrl: './flight-booking.component.html',
  styleUrls: ['./flight-booking.component.scss']
})
export class FlightBookingComponent implements OnInit {
  displayModal: boolean = false;
  constructor(){}

  ngOnInit(): void {
    //
  }
}
