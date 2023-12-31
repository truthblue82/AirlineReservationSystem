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
    firstName: true, email: true, lastName: true,
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
        firstName: '', email: '',
        lastName: '', phone: '',
        password: '', roles: []};
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

      return;
    }

    this.displayModal = true;
    const data: User = this.user;
    this.userSvc.signUp(data).subscribe(
      (result: any) => {
        if(result.status === 200) {
          this.toastr.success('User Created Successfully', 'Please Login');
        } else {
          this.toastr.error('User registered unsuccessfully!', 'Error');
        }
        this.displayModal = false;
        this.router.navigate(['/']);
      },
      (error) => {
        this.toastr.error('Something went wrong!', 'Error');
        this.displayModal = false;
      }
    );
  }

  validateName(el: HTMLInputElement):void {
    let flag = /^[a-zA-Z]+$/.test(el.value);
    this.flags[el.name] = flag;
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
    this.flags.signupBtn = this.flags.firstName
    &&this.flags.lastName
    &&this.flags.email
    &&this.flags.phone
    &&this.flags.password
    &&this.flags.repeatPassword
  }
}
