import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  displayModal:boolean = false;
  curUser: User;

  constructor(
    private appTitle: Title,
    private userSvc: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Profile');
    this.curUser = {email:'', firstName: '', lastName: '', phone: '', roles: []};
  }

  ngOnInit(): void {
    this.displayModal = true;
    this.userSvc.getCurrentUser().subscribe(
      (result: any) => {
        if(result.length > 0) {
          this.curUser = result[0];
          console.log('cur user',this.curUser);
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
  goToUpdate(): void {
    this.router.navigate(['/account-settings']);
  }
}
