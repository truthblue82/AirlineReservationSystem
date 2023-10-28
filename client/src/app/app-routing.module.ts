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
import { ProfileComponent } from './pages/profile/profile.component';
import { FlightHistoryComponent } from './pages/flight-history/flight-history.component';
import { adminGuard } from './guards/admin.guard';
import { userGuard } from './guards/user.guard';
import { FlightBookingListComponent } from './pages/flight-booking-list/flight-booking-list.component';

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
    canActivate: [adminGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [userGuard]
  },
  {
    path: 'airports',
    component: AirportComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'add-airport',
    component: AddAirportComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'edit/:code',
    component: AddAirportComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'flights',
    component: FlightComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'add-flight',
    component: AddFlightComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'flight-history',
    component: FlightHistoryComponent,
    canActivate: [userGuard]
  },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    canActivate: [userGuard]
  },
  {
    path: 'schedules',
    component: ScheduleComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'add-schedule',
    component: AddScheduleComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'flight-booking',
    component: FlightBookingComponent,
  },
  {
    path: 'flight-booking-list',
    component: FlightBookingListComponent,
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
