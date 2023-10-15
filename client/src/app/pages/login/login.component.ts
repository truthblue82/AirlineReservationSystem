import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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
  //user: User;
  username: string;
  password: string;
  rememberMe: boolean;

  constructor(
    private userSvc: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.users = [];
    this.username = '';
    this.password = '';
    this.rememberMe = false;
  }

  ngOnInit(): void {
    this.userSvc.getCurrentUser().subscribe((userData) => {
      this.users = userData;
      console.log('users', userData);
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
    this.userSvc.authenticate(data).subscribe((result: any) => {
      console.log(result);

      if(result.hasOwnProperty('accessToken')) {
        if(!result.accessToken) {
          this.toastr.error('Something went wrong!', 'Error');
          this.displayModal = false;
          return;
        }
      } else {
        this.toastr.error('Something went wrong!', 'Error');
        this.displayModal = false;
        return;
      }

      this.userSvc.storeSession(result);
      this.router.navigate(['/']);
    },
    (err) => {
      console.log(err);
      this.toastr.error(err, 'Error');
      this.displayModal = false;
    });
  }
}
