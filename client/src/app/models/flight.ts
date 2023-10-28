export interface Flight {
  flightNo: string;
  carrierName: string;
  flightModel: string;
  seatCapacity: number;
}

export interface Flights {
  flights: Flight[];
}
