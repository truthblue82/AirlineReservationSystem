import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

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

  constructor(
    private appTitle: Title,
    private router: Router,
    private toastr: ToastrService
  ){
    this.appTitle.setTitle('Airport Reservation System - Schedule Management');
  }

  ngOnInit(): void {
    //
  }

  handleFormSubmit(event: Event):void {
    //
  }
  goToAddSchedual():void {
    this.router.navigate(['/add-schedule']);
  }
}
