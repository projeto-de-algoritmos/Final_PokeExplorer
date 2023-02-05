import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {PokemonSelectComponent} from "./home/pokemon-select/pokemon-select.component";
import {AreaSelectComponent} from "./home/area-select/area-select.component";
import {InitPageComponent} from "./home/init-page/init-page.component";
import {ShortestPathComponent} from "./home/shortest-path/shortest-path.component";

const routes: Routes = [{
  path: '',
  component: HomeComponent,
  children: [
    {
      path: '',
      component: InitPageComponent,
    },
    {
      path: 'pokemons',
      component: PokemonSelectComponent,
    },
    {
      path: 'select-area',
      component: AreaSelectComponent,
    },
    {
      path: 'shortest-path',
      component: ShortestPathComponent,
    },
  ]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
