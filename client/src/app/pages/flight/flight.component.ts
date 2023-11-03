import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Flight, Flights } from 'src/app/models/flight';
import { FlightService } from 'src/app/services/flight.service';
import { CustomDialogComponent } from 'src/app/shared/custom-dialog/custom-dialog.component';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {
  displayModal: boolean = false;
  flights?: Flight[];

  constructor(
    private appTitle:Title,
    private router:Router,
    private flightSvc: FlightService,
    private toastr:ToastrService,
    public dialog: MatDialog
  ){
    this.appTitle.setTitle('Airport Reservation System - Flight Manangement');
    this.flights = [];
  }

  ngOnInit(): void {
    this.flightSvc.getAllFlights()
    .subscribe((value: Flights) => this.flights = value.flights);
  }

  showAddFlight(): void {
    let customDialog = this.dialog.open(
      CustomDialogComponent,
      {
        data: {
          title: "Add Flight",
          subTitle: "Please input flight information",
          cancelBtn: "Cancel",
          okBtn: "Submit",
          objData: [{
            label: 'flightNo',
            input: '',
            required: true
          }, {
            label: 'carrierName',
            input: '',
            required: true
          }, {
            label: 'flightModel',
            input: '',
            required: true
          }, {
            label: 'seatCapacity',
            input: 0,
            required: true
          }]
        }
      }
    );
    customDialog.afterClosed().subscribe(result => {
      if(result.length) {
        const data = result.reduce((prev: any, cur:any) => {
          if(cur.label === "seatCapacity") {
            prev[cur.label] = parseInt(cur.input);
          }
          else
            prev[cur.label] = cur.input;
          return prev;
        }, {});
        this.flightSvc.addFlight(data).subscribe(
          data => {
            this.flights?.push(data);
          },
          error => {
            console.log('error', error);
            this.toastr.error('Can not add flight!', 'error');
          }
        )
      }
    });
  }
  goToAddFlight():void {
    this.router.navigate(['/add-flight']);
  }
}
