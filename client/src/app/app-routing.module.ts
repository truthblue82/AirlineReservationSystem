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

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signup',
    //loadComponent: () => import('./pages/signup/signup.component').then(m => m.SignupComponent),
    component: SignupComponent
  },
  { path: 'login',
    //loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent),
    component: LoginComponent
  },
  {
    path: 'contact',
    //loadComponent: () => import('./pages/contact-us/contact-us.component').then(m => m.ContactUsComponent),
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
    component: AirportComponent,
    //loadComponent: () => import('./pages/airport/airport.component').then(m => m.AirportComponent),
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
    //loadComponent: () => import('./pages/flight/flight.component').then(m => m.FlightComponent),
    canActivate: []
  },
  {
    path: 'add-flight',
    component: AddFlightComponent,
    canActivate: []
  },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    //loadComponent: () => import('./pages/account-settings/account-settings.component').then(m => m.AccountSettingsComponent),
    canActivate: []
  },
  {
    path: 'schedule',
    component: ScheduleComponent,
    //loadComponent: () => import('./pages/schedule/schedule.component').then(m => m.ScheduleComponent),
    canActivate: []
  },
  {
    path: 'add-schedule',
    component: AddScheduleComponent,
    canActivate: []
  },
  {
    path: 'flight-booking',
    component: FlightBookingComponent,
    //loadComponent: () => import('./pages/flight-booking/flight-booking.component').then(m => m.FlightBookingComponent),
    canActivate: []
  },
  {
    path: 'about',
    component: AboutUsComponent,
    //loadComponent: () => import('./pages/about-us/about-us.component').then(m => m.AboutUsComponent),
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
