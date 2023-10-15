import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Title } from '@angular/platform-browser';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  users: User[];
  user: User;
  repeatPassword: string;

  displayModal: boolean = false;
  flags: any = {
    username: true, email: true, fullname: true,
    phone: true, password: true, repeatPassword: true,
    signupBtn: false
  }

  constructor(
    private appTitle: Title,
    private router: Router,
    private userSvc: UserService,
    private toastr: ToastrService
    ) {
      this.appTitle.setTitle('Airport Reservation System - Sign Up');
      this.users = [];
      this.user = {
        username: '', email: '',
        fullname: '', phone: '',
        password: '', active: true, roles: ['1'], token: ''};
      this.repeatPassword = '';
  }

  ngOnInit(): void {
    this.userSvc.getCurrentUser().subscribe((userData) => {
      this.users = userData;
    });
    if(this.users.length > 0) {
      this.router.navigate(['/']);
    }
  }

  handleSubmit(event: Event): void {
    event.preventDefault();

    if(this.user.password !== this.repeatPassword) {
      this.toastr.error('Password not matched!', 'Error');

      return console.log('Password not matched');
    }
    this.displayModal = true;
    const data: User = this.user;
    this.userSvc.signUp(data).subscribe(
      (result: any) => {
        if(result.message === "User registered successfully!") {
          this.toastr.success('Account Created Successfully', 'Please Login');
        } else {
          this.toastr.error('User registered un successfully!', 'Error');
        }
        this.displayModal = false;
        this.router.navigate(['/login']);
      },
      (error) => {
        this.toastr.error('Something went wrong!', 'Error');
        this.displayModal = false;
      }
    );
  }

  validateUsername():void {
    let flag = /^[a-zA-Z]+$/.test(this.user.username);
    this.flags.username = flag;
  }
  validateFullname():void {
    let flag = /^[a-zA-Z ]+$/.test(this.user.fullname);
    this.flags.fullname = flag;
  }
  validatePhone():void {
    let flag = /^[0-9]{10}$/.test(this.user.phone);

    this.flags.phone = flag;
  }
  validateEmail(el: HTMLInputElement):void {
    this.flags.email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(el.value);
  }

  comparePassword():void {
    if(this.user.password !== this.repeatPassword) {
      this.flags.repeatPassword = false;
    } else {
      this.flags.repeatPassword = true;
    }
  }

  enableButton():void {
    this.flags.signupBtn = this.flags.username
    &&this.flags.fullname
    &&this.flags.email
    &&this.flags.fullname
    &&this.flags.phone
    &&this.flags.password
    &&this.flags.repeatPassword
  }
}
