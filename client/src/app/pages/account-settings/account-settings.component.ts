import { Component, OnInit } from '@angular/core';
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
  curUserData: User[] = [];

  fullname: string = '';
  phone: string = '';

  //change password
  oldPassword: string = '';
  password: string = '';
  confirmPassword: string = '';

  //delete account
  deleteAccountPwd: string = '';
  isSureToDelete: boolean = false;
  isAgreeToDelete: boolean = false;

  flags: any = {
    fullname: true, phone: true, comparePassword: true
  }

  constructor(
    private router: Router,
    private userSvc: UserService,
    private toastr: ToastrService
  ) {
    this.curUserData = [];

    this.fullname = '';
    this.phone = '';

    this.oldPassword = '';
    this.password = '';
    this.confirmPassword = '';

    this.deleteAccountPwd = '';
    this.isAgreeToDelete = false;
    this.isSureToDelete = false;
  }

  ngOnInit(): void {
    this.displayModal = true;
    this.userSvc.getCurrentUser().subscribe(
      (result: any) => {
        if(result.length > 0) {
          this.curUserData.push(result[0]);
          this.fullname = result[0].fullname;
          this.phone = result[0].phone;
        } else {
          this.toastr.error('Something went wrong!', 'Error');
        }
        this.displayModal = false;
      },
      (error) => {
        console.log('Error Occured:', error.console.error.msq);
        this.toastr.error(error.error.msg, 'Error');
        this.displayModal = false;
      }
    )
  }

  validateFullname():void {
    let flag = /^[a-zA-Z ]+$/.test(this.fullname);
    this.flags.fullname = flag;
  }
  validatePhone():void {
    let flag = /^[0-9]{10}$/.test(this.phone);

    this.flags.phone = flag;
  }
  comparePassword():void {
    if(this.password !== this.confirmPassword) {
      this.flags.comparePassword = false;
    } else {
      this.flags.comparePassword = true;
    }
  }

  handleUpdateAccoutDetails(event: Event) {
    event.preventDefault();

    const data = {
      name: this.fullname,
      phone: this.phone
    };
    this.displayModal = true;
    this.userSvc.updateAccountDetails(data)
  }

  handleChangePassword(event: Event) {
    event.preventDefault();
  }
}
