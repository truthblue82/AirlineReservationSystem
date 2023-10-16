import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { ContactUsComponent } from './pages/contact-us/contact-us.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';
import { AirportComponent } from './pages/airport/airport.component';
import { FlightComponent } from './pages/flight/flight.component';
import { AccountSettingsComponent } from './pages/account-settings/account-settings.component';
import { ScheduleComponent } from './pages/schedule/schedule.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signup', component: SignupComponent},
  { path: 'login', component: LoginComponent},
  {
    path: 'contact',
    component: ContactUsComponent
  },
  {
    path: 'add-airport',
    component: AirportComponent,
    canActivate: []
  },
  {
    path: 'add-flight',
    component: FlightComponent,
    canActivate: []
  },
  {
    path: 'account-settings',
    component: AccountSettingsComponent,
    canActivate: []
  },
  {
    path: 'add-schedule',
    component: ScheduleComponent,
    canActivate: []
  },
  {
    path: 'about',
    component: AboutUsComponent
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
