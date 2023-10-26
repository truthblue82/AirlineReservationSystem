import { Component, OnInit } from '@angular/core';
import { ScheduledFlight } from 'src/app/models/scheduledFlight';
import { ScheduledFlights } from 'src/app/models/scheduledFlights';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-flight-booking-list',
  templateUrl: './flight-booking-list.component.html',
  styleUrls: ['./flight-booking-list.component.scss']
})
export class FlightBookingListComponent implements OnInit {

  displayModal: boolean = false;
  scheduledFlights: ScheduledFlight[] | undefined;

  constructor(private bookingService: BookingService){}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.scheduledFlights = this.bookingService.scheduledFlights;
  }
bookFlight(arg0: ScheduledFlight) {

}

}
