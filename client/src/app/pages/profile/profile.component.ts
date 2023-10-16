import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  displayModal:boolean = false;
  curUser: any[];

  constructor(
    private appTitle: Title,
    private userSvc: UserService,
    private toastr: ToastrService
  ) {
    this.appTitle.setTitle('Airport Reservation System - Profile');
    this.curUser = [];
  }

  ngOnInit(): void {
    this.displayModal = true;
    this.userSvc.getCurrentUser().subscribe(
      (result: any) => {
        if(result.length > 0) {
          this.curUser=result[0];
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
}
