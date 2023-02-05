import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-init-page',
  templateUrl: './init-page.component.html',
  styleUrls: ['./init-page.component.css']
})
export class InitPageComponent {

  constructor(
    private router: Router,
  ) {}

  nextPage() {
    this.router.navigate(['pokemons'])
  }
}
