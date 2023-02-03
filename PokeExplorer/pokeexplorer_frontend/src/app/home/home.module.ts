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
import {ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import { PokemonNotFoundComponent } from './pokemon-not-found/pokemon-not-found.component';
import { PokemonFoundComponent } from './pokemon-found/pokemon-found.component';


@NgModule({
  declarations: [
    HomeComponent,
    PokemonSelectComponent,
    PokemonNotFoundComponent,
    PokemonFoundComponent,
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
    MatDialogModule
  ],
  exports: [HomeComponent]
})
export class HomeModule { }
