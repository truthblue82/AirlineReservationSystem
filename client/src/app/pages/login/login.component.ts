import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Title } from '@angular/platform-browser';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  displayModal:boolean = false;
  users: User[];
  username: string;
  password: string;
  rememberMe: boolean;

  constructor(
    private appTitle: Title,
    private userSvc: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Sign In');
    this.users = [];
    this.username = '';
    this.password = '';
    this.rememberMe = false;
  }

  ngOnInit(): void {
    this.userSvc.getCurrentUser().subscribe((userData) => {
      this.users = userData;
    });
    if(this.users.length > 0) {
      this.router.navigate(['/']);
    }
  }

  handleLogin(event: Event): void {
    event.preventDefault();

    const data = {
      username: this.username,
      password: this.password
    };
    this.displayModal = true;
    // this.userSvc.authenticate(data).subscribe((result: any) => {
    //   result.body.rememberMe = this.rememberMe;

    //   if(result.status === 200) {
    //     this.userSvc.storeSession(result.body);
    //     this.router.navigate(['/']);
    //   } else {
    //     this.toastr.error('Something went wrong!', 'Error');
    //     this.displayModal = false;
    //   }
    // },
    // (err) => {
    //   console.log(err);
    //   this.toastr.error(err.error.message, 'Error');
    //   this.displayModal = false;
    // });
  }
}
