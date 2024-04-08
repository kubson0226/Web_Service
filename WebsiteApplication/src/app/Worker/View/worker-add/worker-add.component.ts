import {Component, OnInit} from '@angular/core';
import {WorkerService} from "../../Service/worker-service/WorkerService";
import {ActivatedRoute, Router} from '@angular/router';
import {WorkerForm} from "../../Entity/WorkerForm";
import {RestaurantService} from "../../../Restaurant/Service/restaurant.service";
import {Restaurants} from "../../../Restaurant/Entity/Restaurants";

@Component({
  selector: 'app-worker-add',
  templateUrl: './worker-add.component.html',
  styleUrls: ['./worker-add.component.css']
})
export class WorkerAddComponent implements OnInit {

  worker: WorkerForm = {
    age: 0,
    name: '',
    restaurant_id:''
  };

  constructor(
    private workerService: WorkerService,
    private restaurantService: RestaurantService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params =>{
    this.restaurantService.getRestaurant(params['uuid'])
      .subscribe(restaurant => {
        this.worker.restaurant_id = restaurant.id;
      })
    })
  }

  onSubmit(): void {
    this.workerService.postWorker(this.worker.restaurant_id, this.worker)
      .subscribe(() => this.router.navigate([`/workers//${this.worker.restaurant_id}`]));
  }

}

/*
this.route.params.subscribe(params => {

  this.restaurantService.getRestaurant(params['uuid'])
    .subscribe(restaurant => {
      this.uuid = restaurant.id;
      this.restaurant = {
        name: restaurant.name,
        numberOfSits: restaurant.numberOfSits
      };
      this.original = {...this.restaurant};
    });
});*/
