import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
// import { AuthenticationService } from './_service/app.authenticationservice';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  // btnFlag!: boolean;
  // username!: string;
  // isUser!: boolean;
  // isAdmin!: boolean;
  adminRole: string = "ROLE_ADMIN";
  user!: any[];

  showWelcomeHeader!: boolean;
  imgSrc = 'assets/images/menu2.png';

  constructor(private router: Router ) {
    this.user = [];
    this.showWelcomeHeader = true;
  }

  ngOnInit(): void {
    if(sessionStorage.getItem('showWelcomeHeader')) {
      this.showWelcomeHeader = false;
    }

    // this.userService.getCurrentUser().subscribe((user) => {
    //   this.user = user;
    // });

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
    sessionStorage.clear();
    //this.userService.logoutUser();
    this.router.navigate(['login']);
  }

  handleHeaderRemove() {
    this.showWelcomeHeader = false;
    sessionStorage.setItem('showWelcomeHeader', 'false');
  }
}
