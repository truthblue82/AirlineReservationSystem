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
  curUserData: User[];

  fullname: string;
  phone: string;

  //change password
  oldPassword: string;
  password: string;
  confirmPassword: string;

  //delete account
  deleteAccountPwd: string;
  isSureToDelete: boolean;
  isAgreeToDelete: boolean;

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
        console.log('user account', result);
        console.log(result.status);
        //if(result.status)
      },
      (error) => {
        console.log('Error Occured:', error.console.error.msq);
        this.toastr.error(error.error.msg, 'Error');
        this.displayModal = false;
      }
    )
  }

  handleUpdateAccoutDetails(event: Event) {
    event.preventDefault();
  }

  handleChangePassword(event: Event) {
    event.preventDefault();
  }
}
