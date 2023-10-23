import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-airport',
  templateUrl: './add-airport.component.html',
  styleUrls: ['./add-airport.component.scss']
})
export class AddAirportComponent implements OnInit {
  displayModal: boolean = false;

  constructor() {}

  ngOnInit(): void {
    //
  }
}
