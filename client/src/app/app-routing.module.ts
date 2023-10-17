import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';
import { AirportComponent } from './pages/airport/airport.component';
import { FlightComponent } from './pages/flight/flight.component';
import { AccountSettingsComponent } from './pages/account-settings/account-settings.component';
import { ScheduleComponent } from './pages/schedule/schedule.component';
import { FlightBookingComponent } from './pages/flight-booking/flight-booking.component';
import { ContactUsComponent } from './pages/contact-us/contact-us.component';
import { AddScheduleComponent } from './pages/add-schedule/add-schedule.component';
import { AddAirportComponent } from './pages/add-airport/add-airport.component';
import { AddFlightComponent } from './pages/add-flight/add-flight.component';
import { UserComponent } from './pages/user/user.component';
import { AddUserComponent } from './pages/add-user/add-user.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { FlightHistoryComponent } from './pages/flight-history/flight-history.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signup',
    component: SignupComponent
  },
  { path: 'login',
    component: LoginComponent
  },
  {
    path: 'contact',
    component: ContactUsComponent
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: []
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: []
  },
  {
    path: 'add-user',
    component: AddUserComponent,
    canActivate: []
  },
  {
    path: 'airport',
    loadChildren: () => import('./pages/airport/airport.component').then(m => m.AirportComponent),
    canActivate: []
  },
  {
    path: 'add-airport',
    component: AddAirportComponent,
    canActivate: []
  },
  {
    path: 'flight',
    component: FlightComponent,
    canActivate: []
  },
  {
    path: 'add-flight',
    component: AddFlightComponent,
    canActivate: []
  },
  {
    path: 'flight-history',
    component: FlightHistoryComponent,
    canActivate: []
  },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    canActivate: []
  },
  {
    path: 'schedule',
    component: ScheduleComponent,
    canActivate: []
  },
  {
    path: 'add-schedule',
    component: AddAirportComponent,
    canActivate: []
  },
  {
    path: 'flight-booking',
    component: FlightBookingComponent,
    canActivate: []
  },
  {
    path: 'about',
    component: AboutUsComponent,
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
