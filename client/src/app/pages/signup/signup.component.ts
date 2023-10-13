import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  user!: User;
  repeatPassword!: string;
  displayModal: boolean = false;

  constructor() {
    this.repeatPassword = '';
  }

  ngOnInit(): void {
      //
  }

  handleSubmit(event: Event) {
    event.preventDefault();

    if(this.user.password !== this.repeatPassword) {
      //this.toastr.error('Error', 'Password not matched!');
      return console.log('Password not matched');
    }
    //const username = this.email.split('@')[0];
  }
}
