import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {PokemonService} from '../services/pokemon.service';
import {MatDialog} from "@angular/material/dialog";
import {Pokemons} from "../interfaces/pokemons";
import {PokemonNotFoundComponent} from "./pokemon-not-found/pokemon-not-found.component";
import {PokemonFoundComponent} from "./pokemon-found/pokemon-found.component";
import {ModalInstructionsComponent} from "../modal-instructions/modal-instructions.component";


@Component({
  selector: 'app-pokemon-select',
  templateUrl: './pokemon-select.component.html',
  styleUrls: ['./pokemon-select.component.css']
})
export class PokemonSelectComponent implements OnInit {

  list: Pokemons[] | undefined;
  pokemon: any = null;
  pokemonForm = this.formBuilder.group({
    pokemon: '',
  });

  title: string = "Escolha do seu time!"

  instructions: string[] = [
    "Esta é a página onde você selecionará as opções que podem compor o seu time Pokemon!",
    "Em seguida, será calculado o melhor time possível entre as opções, que será usado para as próximas etapas.",
    "Para selecionar as opções, clique em cima do quadrado do pokemon desejado, que ficará verde indicando que está selecionado, em seguida clique em continuar."
  ]

  constructor(
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {
    this.openInitDialog(this.title, this.instructions);
    this.pokemonService.listOptionsPokemons().forEach((pokemon)=> this.list = pokemon);
  }

  openDialogError(): void {
    const dialogRef = this.dialog.open(PokemonNotFoundComponent, {
      width: '500px',
      height: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDialog(response: any): void {
    const dialogRef = this.dialog.open(PokemonFoundComponent, {
      width: '700px',
      height: '500px',
      data: response,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  find() {
    let selectedList: Array<Pokemons> = []

    console.log(this.list);

    this.list?.forEach((pokemon) => {
        if (pokemon.selected){
          selectedList.push(pokemon);
        }
    })


    this.pokemonService.bestTeam(selectedList).subscribe((response: any) => {
      this.openDialog(response);
      }, (error: any) => {
      this.openDialogError();
      }
    );
  }

  setSelected(pokemon: Pokemons) {
    pokemon.selected = !pokemon.selected
  }

  selectAll() {
    this.list?.forEach((pokemon) => {
      pokemon.selected = true;
    })
  }

  unselectAll() {
    this.list?.forEach((pokemon) => {
      pokemon.selected = false;
    })
  }

  private openInitDialog(title: any, instructions: any) {
    this.dialog.open(ModalInstructionsComponent, {
      width: '800px',
      height: '440px',
      data: {title, instructions}
    });

  }
}
