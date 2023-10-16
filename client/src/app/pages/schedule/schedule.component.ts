import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {
  displayModal: boolean = false;
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

  constructor(){
  }

  ngOnInit(): void {
    //
  }

  handleFormSubmit(event: Event):void {
    //
  }
}
