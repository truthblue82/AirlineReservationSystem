import { Component, Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldControl } from '@angular/material/form-field';

@Component({
  selector: 'app-custom-dialog',
  templateUrl: './custom-dialog.component.html',
  styleUrls: ['./custom-dialog.component.scss']
})
export class CustomDialogComponent {
  title: string;
  subtitle: string;
  okBtn: string;
  cancelBtn: string;
  objData: any[];

  constructor(
    public dialogRef: MatDialogRef<CustomDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CustomDialogModel
  ) {
    this.title = data.title;
    this.subtitle = data.subtitle;
    this.okBtn = data.okBtn;
    this.cancelBtn = data.cancelBtn;
    this.objData = data.objData;
  }

  onDismiss(): void {
    this.dialogRef.close(false);
  }

  onAction(): void {
    this.dialogRef.close(this.objData);
  }
}

export class CustomDialogModel {
  constructor(
    public title: string,
    public subtitle: string,
    public cancelBtn: string,
    public okBtn: string,
    public objData: any[]
  ) {}
}
