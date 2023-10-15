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
      console.log('user header', this.user);
      this.isUser = this.user[0]?.roles[0] === this.userRole ? true : false;
      console.log('role user', this.isUser);
      this.isAdmin = this.user[0]?.roles[0] === this.adminRole ? true : false;
      console.log('role admin', this.isAdmin);
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
