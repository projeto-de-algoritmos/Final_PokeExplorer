import {Component, OnInit} from '@angular/core';
import {ModalInstructionsComponent} from "../modal-instructions/modal-instructions.component";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";

export interface Path {
  areas: any[];
}

@Component({
  selector: 'app-shortest-path',
  templateUrl: './shortest-path.component.html',
  styleUrls: ['./shortest-path.component.css']
})

export class ShortestPathComponent implements OnInit{

  constructor(
    public dialog: MatDialog,
    private router: Router,
  ) {

  }

  path: any

  title: string = "Fim da Jornada!"

  instructions: string[] = [
    "Seu caminho está pronto!",
    "Obrigado Por participar e Boa viagem!",
    "Clique no botão abaixo para visualizar o caminho calculado.",
    "Feito por Artur e Yan :)"
  ]


  ngOnInit(): void {
    this.openInitDialog(this.title, this.instructions)
    this.path = history.state.data
  }


  private openInitDialog(title: any, instructions: any) {
    this.dialog.open(ModalInstructionsComponent, {
      width: '800px',
      height: '440px',
      data: {title, instructions}
    });

  }

  home() {
    this.router.navigate(['']);
  }
}
