import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isUser: boolean;
  isAdmin: boolean;
  adminRole: string = "ROLE_ADMIN";
  userRole: string = "ROLE_CUSTOMER";
  user: any[];

  imgSrc = 'assets/images/menu2.png';

  constructor(
    private router: Router,
    private userSvc: UserService
    ) {
    this.user = [];
    this.isAdmin = false;
    this.isUser = false;
  }

  ngOnInit(): void {
    this.userSvc.getCurrentUser().subscribe((user) => {
      this.user = user;
    });

    // this.isUser = false;
    // this.isAdmin = false;
    // if(sessionStorage.getItem('role') === 'user') {
    //   this.isUser = true;
    // } else if(sessionStorage.getItem('role') === 'admin') {
    //   this.isAdmin = true;
    // }
    // // this.btnFlag = this.authenticationService.isUserLoggedIn();
    // this.username = sessionStorage.getItem('username') ?? '';
    // if(this.username != null)
    //   this.username = this.username.toUpperCase();
  }

  showUser() {
    console.log('user:', this.user);
  }

  handleLogout() {
    this.userSvc.logout();
    this.router.navigate(['login']);
  }
}
