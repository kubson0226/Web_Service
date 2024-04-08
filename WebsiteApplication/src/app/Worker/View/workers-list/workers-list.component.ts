import {Component, OnInit} from '@angular/core';
import {Restaurants} from "../../../Restaurant/Entity/Restaurants";
import {RestaurantService} from "../../../Restaurant/Service/restaurant.service";
import {Restaurant} from "../../../Restaurant/Entity/Restaurant";
import {Worker} from "../../Entity/Worker";
import {Workers} from "../../Entity/Workers";
import {WorkerService} from "../../Service/worker-service/WorkerService";

@Component({
  selector: 'app-workers-list',
  templateUrl: './workers-list.component.html',
  styleUrl: './workers-list.component.css'
})
export class WorkersListComponent implements OnInit{

  workers: Workers | undefined;

  constructor(private service: WorkerService) {

  }

  ngOnInit() {
    this.service.getWorkers().subscribe(workers => {
      return this.workers = workers;
    });
  }

  onDelete(worker: Worker): void {
    this.service.deleteWorker(worker.id).subscribe(() => this.ngOnInit());
  }
}
