import { Component, OnInit } from '@angular/core';
// import { AuthenticationService } from './_service/app.authenticationservice';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  btnFlag!: boolean;
  username!: string;
  user!: boolean;
  admin!: boolean;

  constructor() {}

  ngOnInit(): void {
    this.user = false;
    this.admin = false;
    if(sessionStorage.getItem('role') === 'user') {
      this.user = true;
    } else if(sessionStorage.getItem('role') === 'admin') {
      this.admin = true;
    }
    // this.btnFlag = this.authenticationService.isUserLoggedIn();
    this.username = sessionStorage.getItem('username') ?? '';
    if(this.username != null)
      this.username = this.username.toUpperCase();
  }
}
