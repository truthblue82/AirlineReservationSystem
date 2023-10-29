import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.scss']
})
export class AccountSettingsComponent implements OnInit {
  displayModal = false;
  curUser: User;

  //change password
  oldPassword: string = '';
  password: string = '';
  confirmPassword: string = '';

  flags: any = {
    phone: true, firstName: true, lastName: true, updateBtn: true
  }

  constructor(
    private appTitle: Title,
    private router: Router,
    private userSvc: UserService,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Account Setting');
    this.curUser = {
      email: '',
      firstName: '', lastName: '', phone: '', roles: []};

    this.oldPassword = '';
    this.password = '';
    this.confirmPassword = '';
  }

  ngOnInit(): void {
    this.displayModal = true;
    this.userSvc.getCurrentUser().subscribe(
      (result: any) => {
        if(result.length > 0) {
          this.curUser = result[0];
        } else {
          this.toastr.error('Something went wrong!', 'Error');
        }
        this.displayModal = false;
      },
      (error) => {
        this.toastr.error(error.error.msg, 'Error');
        this.displayModal = false;
      }
    )
  }
  validatePhone():void {
    let flag = /^[0-9]{10}$/.test(this.curUser.phone);
    this.flags.phone = flag;
  }
  validateString(el: HTMLInputElement): void {
    let flag = /^[a-zA-Z ]+$/.test(el.value);
    this.flags[el.name] = flag;
  }

  handleUpdateAccoutDetails(event: Event) {
    event.preventDefault();
    this.displayModal = true;
    this.userSvc.updateAccountDetails(this.curUser)
        .subscribe((result:any) => {
          if(result.status === 200) {
            this.toastr.success('Your information is updated successful!', 'Inform');
            this.userSvc.storeSession(this.curUser);
          } else {
            this.toastr.error('Updated fail!', 'Error');
          }
        },
        (error) => {
          this.toastr.error('Something went wrong!', 'Error');
        });
  }
  enableButton():void {
    this.flags.updateBtn = this.flags.firstName
    &&this.flags.lastName
    &&this.flags.phone
  }
}
