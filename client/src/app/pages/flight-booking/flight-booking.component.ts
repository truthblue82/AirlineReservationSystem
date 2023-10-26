import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Airport } from 'src/app/models/airport';
import { Airports } from 'src/app/models/airports';
import { ScheduledFlightSearch } from 'src/app/models/scheduledFlightSearch';
import { ScheduledFlights } from 'src/app/models/scheduledFlights';
import { AirportService } from 'src/app/services/airport.service';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-flight-booking',
  templateUrl: './flight-booking.component.html',
  styleUrls: ['./flight-booking.component.scss']
})
export class FlightBookingComponent implements OnInit {
  displayModal: boolean = false;
  airportList: Airport[] | undefined;
  selectedDeptAirport: string | undefined;
  selectedArrAirport: string | undefined;
  selectedNoAdults: number | undefined;
  selectedNoChildren: number| undefined;
  departureDate: Date | undefined;

  constructor(private airportService: AirportService,
     private bookingService: BookingService,
     private router: Router,
    private toastr: ToastrService
  ){}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.airportService.getAllAirports().subscribe((value: Airports) => this.airportList = value.airports);
  }

  onSubmit() {
    const noOfPassengers: number = +(this.selectedNoAdults??=0) + +(this.selectedNoChildren??=0);
    const scheduledFlightSearch = new ScheduledFlightSearch();
    scheduledFlightSearch.departureAirportCode = this.selectedDeptAirport;
    scheduledFlightSearch.arrivalAirportCode = this.selectedArrAirport;
    scheduledFlightSearch.noOfPassengers = noOfPassengers;
    scheduledFlightSearch.departureDate = new Date(this.departureDate!).toISOString();

    this.bookingService.findScheduledFlights(scheduledFlightSearch)
    .subscribe((value: ScheduledFlights) => {
      this.bookingService.scheduledFlights = value?.scheduledFlights;
      if(value) {
        this.router.navigate(['flight-booking-list']);
      } else {
        this.toastr.info("No scheduled flight found!", "INFO")
      }
    });
  }
}
