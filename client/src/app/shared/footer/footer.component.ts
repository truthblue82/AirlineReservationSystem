import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  curYear!: number;

  constructor() {}

  ngOnInit(): void {
    this.curYear = new Date().getFullYear();
  }
}
