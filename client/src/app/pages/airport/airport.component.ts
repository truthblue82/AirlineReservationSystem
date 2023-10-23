import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.scss']
})
export class AirportComponent implements OnInit{
  displayModal: boolean = false;

  constructor(
    private appTitle: Title,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Airport Management');
  }

  ngOnInit(): void {
    //
  }
  goToAddAirPort():void{
    this.router.navigate(['/add-airport']);
  }
}
