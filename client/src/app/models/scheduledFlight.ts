import { Flight } from "./flight";
import { Schedule } from "./schedule";

export interface ScheduledFlight {
  scheduledFlightId: number;
  flight: Flight;
  availableSeats: number;
  schedule: Schedule;
  economicPrice: string | number;
}

export interface ScheduledFlights {
  scheduledFlights: ScheduledFlight[];
}
