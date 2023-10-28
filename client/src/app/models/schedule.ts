import { Airport } from "./airport";

export interface Schedule {
  srcAirport: Airport;
  dstnAirport: Airport;
  deptDateTime: string;
  arrDateTime: string;
}

export interface Schedules {
  schedules: Schedule[]
}
