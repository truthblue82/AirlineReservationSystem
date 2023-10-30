import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, map } from 'rxjs';
import { Airport, Airports } from 'src/app/models/airport';
import { AirportService } from 'src/app/services/airport.service';
import { CustomDialogComponent } from 'src/app/shared/custom-dialog/custom-dialog.component';

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.scss']
})
export class AirportComponent implements OnInit{
  displayModal: boolean = false;
  airPorts?: Airport[];
  airPort?: Airport;

  constructor(
    private appTitle: Title,
    private router: Router,
    private airPortSvc: AirportService,
    private toastr: ToastrService,
    public dialog: MatDialog
  ) {
    this.appTitle.setTitle('Airport Reservation System - Airport Management');
    this.airPort = {
      code: '', name: '', location: ''
    };
  }

  showAddAirport(): void {
    let customDialog = this.dialog.open(
      CustomDialogComponent,
      {
        data: {
          title: "Add Airport",
          subTitle: "Please input airport information",
          cancelBtn: "Cancel",
          okBtn: "Submit",
          objData: [{
            label: 'code',
            input: '',
            required: true
          }, {
            label: 'name',
            input: '',
            required: true
          }, {
            label: 'location',
            input: '',
            required: true
          }]
        }
      }
    );
    customDialog.afterClosed().subscribe(result => {
      if(result.length) {
        const data = result.reduce((prev: any, cur:any) => {
          prev[cur.label] = cur.input;
          return prev;
        }, {});
        this.airPortSvc.addAirport(data).subscribe(
          data => {
            this.airPorts?.push(data);
          },
          error => {
            console.log('error', error);
          });
      }
    });
  }

  ngOnInit(): void {
    this.airPortSvc.getAllAirports().subscribe((value: Airports) => this.airPorts = value.airports);
  }

  deleteAirport(code: string): void {
    //
  }
}
