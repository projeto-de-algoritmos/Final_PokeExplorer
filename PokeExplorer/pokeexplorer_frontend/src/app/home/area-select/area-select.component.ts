import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from '../services/pokemon.service';
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {AreaService} from "../services/area.service";
import {Area} from "../interfaces/areas";
import {PokemonFoundComponent} from "../pokemon-select/pokemon-found/pokemon-found.component";
import {ModalInstructionsComponent} from "../modal-instructions/modal-instructions.component";
import {Router} from "@angular/router";


@Component({
  selector: 'app-pokemon-select',
  templateUrl: './area-select.component.html',
  styleUrls: ['./area-select.component.css']
})
export class AreaSelectComponent implements OnInit {

  userTeam: any[] | undefined;
  optionsAreas: any[] = [];
  imageInitialArea: any;
  imageFinalArea: any;
  initialArea: any = null;
  finalArea: any = null;

  areaForm = this.formBuilder.group({
    initialArea: '',
    finalArea: '',
  });

  title: string = "Escolha o caminho!"

  instructions: string[] = [
    "Agora escolha de onde o seu personagem irá começar e onde quer chegar!",
    "Assim, o algoritmo calculará, baseado nos treinadores de cada área, qual é o caminho com as batalhas mais fáceis!",
    "Para selecionar as opções, clique na caixa abaixo dos pontos de interrogação e em seguida clique na opção desejada."
  ]


  constructor(
    private areaService: AreaService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private router: Router,
  ) {

  }

  ngOnInit(): void {
    this.userTeam = history.state.data;
    this.openInitDialog(this.title, this.instructions);
    this.areaService.listArea().subscribe((response: any) => {
      response.map((area: Area) => {
        this.extractArea(area);
      });
      this.optionsAreas = response;
    })
  }

  private openInitDialog(title: any, instructions: any) {
    this.dialog.open(ModalInstructionsComponent, {
      width: '800px',
      height: '440px',
      data: {title, instructions}
    });

  }

  extractArea(area: Area) {
    return {
      id: area.id,
      name: area.name,
      url: area.url
    };
  }

  loadInitialArea($event: MatSelectChange) {
    if($event.value !== null){
      this.imageInitialArea = $event.value.url
      this.initialArea = $event.value.id
    } else {
      this.initialArea = $event.value
    }
  }

  loadFinalArea($event: MatSelectChange) {
    if($event.value !== null){
      this.imageFinalArea = $event.value.url
      this.finalArea = $event.value.id
    } else {
      this.finalArea = $event.value
    }
  }


  shortestPath() {
    this.areaService.findClosestArea(this.initialArea, this.finalArea, this.userTeam).subscribe(path =>{
      this.router.navigate(['shortest-path'], {state: { data: path}});
    })
  }
}
