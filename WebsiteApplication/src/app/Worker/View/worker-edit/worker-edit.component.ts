import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Restaurants} from "../../../Restaurant/Entity/Restaurants";
import {WorkerForm} from "../../Entity/WorkerForm";
import {WorkerService} from "../../Service/worker-service/WorkerService";
import {RestaurantService} from "../../../Restaurant/Service/restaurant.service";
import {WorkerEdit} from "../../Entity/WorkerEdit";

@Component({
  selector: 'app-worker-edit',
  templateUrl: './worker-edit.component.html',
  styleUrls: ['./worker-edit.component.css']
})
export class WorkerEditComponent implements OnInit {

  uuid: string | undefined;

  worker: WorkerEdit | undefined;

  original: WorkerEdit | undefined;

  restaurants: Restaurants | undefined;

  constructor(
    private workerService: WorkerService,
    private restaurantService: RestaurantService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.restaurantService.getRestaurants()
        .subscribe(restaurants => this.restaurants = restaurants);

      this.workerService.getWorker(params['uuid'])
        .subscribe(worker => {
          this.uuid = worker.id;
          this.worker = {
            name: worker.name,
            age: worker.age,
          };
          this.original = {...this.worker};
        });
    });
  }

  onSubmit(): void {
    this.workerService.patchWorker(this.uuid!, this.worker!)
      .subscribe(() => this.router.navigate(['/workers']));
  }

}
