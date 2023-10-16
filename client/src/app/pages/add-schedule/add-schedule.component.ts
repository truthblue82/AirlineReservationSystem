import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-schedule',
  templateUrl: './add-schedule.component.html',
  styleUrls: ['./add-schedule.component.scss']
})
export class AddScheduleComponent implements OnInit{
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

  constructor() {}
  ngOnInit(): void {
    //
  }
  handleFormSubmit(event: Event):void {
    //
  }
}
