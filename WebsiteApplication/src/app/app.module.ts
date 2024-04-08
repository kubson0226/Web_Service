import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestaurantListComponent} from "./Restaurant/View/restaurant-list/restaurant-list.component";
import { RestaurantService} from "./Restaurant/Service/restaurant.service";
import {RestaurantDetailsComponent} from "./Restaurant/View/restaurant-details/restaurant-details.component";
import {WorkersListComponent} from "./Worker/View/workers-list/workers-list.component";
import {WorkerDetailsComponent} from "./Worker/View/worker-details/worker-details.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import {WorkerService} from "./Worker/Service/worker-service/WorkerService";
import {WorkerAddComponent} from "./Worker/View/worker-add/worker-add.component";
import {RestaurantAddComponent} from "./Restaurant/View/restaurant-add/restaurant-add.component";
import {RestaurantEditComponent} from "./Restaurant/View/restaurant-edit/restaurant-edit.component";
import {WorkerEditComponent} from "./Worker/View/worker-edit/worker-edit.component";

/**
 * Application main module.
 */
@NgModule({
  declarations: [
    AppComponent,
    RestaurantListComponent,
    RestaurantDetailsComponent,
    RestaurantAddComponent,
    RestaurantEditComponent,
    WorkersListComponent,
    WorkerDetailsComponent,
    WorkerAddComponent,
    WorkerEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    RestaurantService,
    WorkerService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {

}
