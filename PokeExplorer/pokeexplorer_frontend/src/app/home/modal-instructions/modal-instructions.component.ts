import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
export interface DialogData {
  title: string;
  instructions: string[];
}

@Component({
  selector: 'app-modal-instructions',
  templateUrl: './modal-instructions.component.html',
  styleUrls: ['./modal-instructions.component.css']
})
export class ModalInstructionsComponent implements OnInit{

  constructor(
    public dialogRef: MatDialogRef<ModalInstructionsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  ngOnInit(): void {
    console.log(this.data.title)
  }

  proceed() {
    this.dialogRef.close();
  }
}
