import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AirportService } from 'src/app/services/airport.service';

@Component({
  selector: 'app-add-airport',
  templateUrl: './add-airport.component.html',
  styleUrls: ['./add-airport.component.scss']
})
export class AddAirportComponent implements OnInit {
  displayModal: boolean = false;
  //airports: Airport[];

  constructor(
    private appTitle: Title,
    private airportSvc: AirportService,
    private router: Router,
    private toastr: ToastrService
  ) {
    //this.appTitle.setTitle('')
  }

  ngOnInit(): void {
    //
  }
}
