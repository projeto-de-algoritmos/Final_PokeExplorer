import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import { PokemonSelectComponent } from './pokemon-select/pokemon-select.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";

import {MatGridListModule} from "@angular/material/grid-list";
import {PokemonFoundComponent} from "./pokemon-select/pokemon-found/pokemon-found.component";
import {PokemonNotFoundComponent} from "./pokemon-select/pokemon-not-found/pokemon-not-found.component";
import {ModalInstructionsComponent} from "./modal-instructions/modal-instructions.component";


@NgModule({
  declarations: [
    HomeComponent,
    PokemonSelectComponent,
    PokemonFoundComponent,
    PokemonNotFoundComponent,
    ModalInstructionsComponent,
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        MatIconModule,
        MatToolbarModule,
        MatButtonModule,
        MatFormFieldModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatDialogModule,
        MatGridListModule,
        FormsModule
    ],
  exports: [HomeComponent]
})
export class HomeModule { }
