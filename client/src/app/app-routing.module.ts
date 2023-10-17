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
import { adminGuard } from './guards/admin.guard';
import { userGuard } from './guards/user.guard';

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
    path: 'add-user',
    component: AddUserComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'airport',
    component: AirportComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'add-airport',
    component: AddAirportComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'flight',
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
  // {
  //   path: 'flight-booking-list',
  // },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    canActivate: [userGuard]
  },
  {
    path: 'schedule',
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
