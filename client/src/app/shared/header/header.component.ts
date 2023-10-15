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
  }

  showUser() {
    console.log('user:', this.user);
  }

  handleLogout() {
    this.userSvc.logout();
    this.router.navigate(['login']);
  }
}
