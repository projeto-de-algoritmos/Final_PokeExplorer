import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HomeModule} from "./home/home.module";
import {HttpClientModule} from "@angular/common/http";
import { AreaSelectComponent } from './home/area-select/area-select.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import { InitPageComponent } from './home/init-page/init-page.component';
import { AreasInfoComponent } from './home/areas-info/areas-info.component';

@NgModule({
  declarations: [
    AppComponent,
    AreaSelectComponent,
    InitPageComponent,
    AreasInfoComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        HomeModule,
        FormsModule,
        MatIconModule,
        MatFormFieldModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatButtonModule

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
