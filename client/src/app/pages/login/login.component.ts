import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  displayModal:boolean = false;

  constructor() {}

  ngOnInit(): void {
  }

  handleLogin(event: Event): void {
    event.preventDefault();
  }
}
