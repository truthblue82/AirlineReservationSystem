import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, map } from 'rxjs';
import { Airport, Airports } from 'src/app/models/airport';
import { AirportService } from 'src/app/services/airport.service';
import { ConfirmDialogComponent } from 'src/app/shared/confirm-dialog/confirm-dialog.component';
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

  ngOnInit(): void {
    this.airPortSvc.getAllAirports().subscribe((value: Airports) => this.airPorts = value.airports);
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

  showEditAiport(code: string): void {
    const airport = this.airPorts?.filter(ap => ap.code === code)[0];

    let customDialog = this.dialog.open(
      CustomDialogComponent,
      {
        data: {
          title: "Edit Airport",
          subTitle: "Please input airport information",
          cancelBtn: "Cancel",
          okBtn: "Submit",
          objData: [{
            label: 'code',
            input: airport?.code,
            required: true,
            disabled: true
          }, {
            label: 'name',
            input: airport?.name,
            required: true
          }, {
            label: 'location',
            input: airport?.location,
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
        this.airPortSvc.updateAirport(code, data).subscribe(
          data => {
            let idx = this.airPorts?.findIndex(ap => ap.code === code);
            if(idx)
              this.airPorts?.splice(idx, 1, data);
          },
          error => {
            console.log('error', error);
          });
      }
    });
  }

  deleteAirport(code: string): void {
    let confirm = this.dialog.open(
      ConfirmDialogComponent,
      {
        data: {
          title: 'Delete Airport',
          message: `Are you sure you want to delete airport ${code}?`,
          noTitle: 'Cancel',
          yesTitle: 'Delete'
        }
      }
    );
    confirm.afterClosed().subscribe(result => {
      if(result) {
        this.airPortSvc.deleteAirport(code).subscribe(
          result => {
            this.toastr.success(`Delete the airport ${code} successfully!`, 'Inform');
            location.assign('/airports');
          },
          error => {
            this.toastr.error(`Can not delete the airport ${code}`, 'Error');
          }
        );
      }
    });
  }
}
