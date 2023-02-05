import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Router} from "@angular/router";
export interface DialogData {
  title: string;
  instructions: string[];
}

@Component({
  selector: 'app-pokemon-found',
  templateUrl: './pokemon-found.component.html',
  styleUrls: ['./pokemon-found.component.css']
})
export class PokemonFoundComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<PokemonFoundComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  resposta: any = this.data

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    console.log(this.resposta)
  }

  nextPage() {
    this.dialogRef.close();
    this.router.navigate(['select-area'], {state: { data: this.data}});

  }
}
