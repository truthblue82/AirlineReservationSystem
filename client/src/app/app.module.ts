import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClient } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CookieService } from 'ngx-cookie-service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';
import { AccountSettingsComponent } from './pages/account-settings/account-settings.component';
import { ContactUsComponent } from './pages/contact-us/contact-us.component';
import { FlightBookingComponent } from './pages/flight-booking/flight-booking.component';
import { FlightDetailComponent } from './pages/flight-detail/flight-detail.component';
import { FlightHistoryComponent } from './pages/flight-history/flight-history.component';
import { FlightTicketsComponent } from './pages/flight-tickets/flight-tickets.component';
import { InvoiceComponent } from './pages/invoice/invoice.component';
import { ReportComponent } from './pages/report/report.component';
import { AirportComponent } from './pages/airport/airport.component';
import { FlightComponent } from './pages/flight/flight.component';
import { ScheduleComponent } from './pages/schedule/schedule.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { AddScheduleComponent } from './pages/add-schedule/add-schedule.component';
import { AddAirportComponent } from './pages/add-airport/add-airport.component';
import { AddFlightComponent } from './pages/add-flight/add-flight.component';
import { UserComponent } from './pages/user/user.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { FlightBookingListComponent } from './pages/flight-booking-list/flight-booking-list.component';
import { CustomDialogComponent } from './shared/custom-dialog/custom-dialog.component';
import { ConfirmDialogComponent } from './shared/confirm-dialog/confirm-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    SignupComponent,
    LoginComponent,
    AboutUsComponent,
    AccountSettingsComponent,
    ContactUsComponent,
    FlightBookingComponent,
    FlightBookingListComponent,
    FlightDetailComponent,
    FlightHistoryComponent,
    FlightTicketsComponent,
    InvoiceComponent,
    ReportComponent,
    AirportComponent,
    FlightComponent,
    ScheduleComponent,
    AddScheduleComponent,
    AddAirportComponent,
    AddFlightComponent,
    UserComponent,
    ProfileComponent,
    CustomDialogComponent,
    ConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatProgressSpinnerModule,
    ToastrModule.forRoot(),
  ],
  exports: [
    CustomDialogComponent,
    MatDialogModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: MAT_DIALOG_DEFAULT_OPTIONS,
      useValue: {hasBackdrop: false}
    },
    HttpClient,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
