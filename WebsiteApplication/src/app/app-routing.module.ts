import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListComponent} from "./Restaurant/View/restaurant-list/restaurant-list.component";
import {RestaurantDetailsComponent} from "./Restaurant/View/restaurant-details/restaurant-details.component";
import {WorkersListComponent} from "./Worker/View/workers-list/workers-list.component";
import {WorkerDetailsComponent} from "./Worker/View/worker-details/worker-details.component";
import {WorkerAddComponent} from "./Worker/View/worker-add/worker-add.component";
import {RestaurantAddComponent} from "./Restaurant/View/restaurant-add/restaurant-add.component";
import {RestaurantEditComponent} from "./Restaurant/View/restaurant-edit/restaurant-edit.component";
import {WorkerEditComponent} from "./Worker/View/worker-edit/worker-edit.component";

/**
 * All available routes.
 */
const routes: Routes = [
  {
    component: RestaurantListComponent,
    path: "restaurants"
  },
  {
    component: RestaurantAddComponent,
    path: "restaurants/add"
  },
  {
    component: RestaurantDetailsComponent,
    path:"restaurants/:uuid"
  },
  {
    component: WorkerAddComponent,
    path:"restaurants/:uuid/worker"
  },
  {
    component:RestaurantEditComponent,
    path: "restaurants/:uuid/edit"
  },
  {
    component: WorkersListComponent,
    path:"workers"
  },
  {
    component:WorkerDetailsComponent,
    path:"workers/:uuid"
  },
  {
    component:WorkerEditComponent,
    path: "workers/:uuid/edit"
  }
];

/**
 * Global routing module.
 */
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {

}
