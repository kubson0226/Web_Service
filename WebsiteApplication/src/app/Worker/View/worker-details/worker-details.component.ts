import { Component, OnInit } from '@angular/core';
import {WorkerService} from "../../Service/worker-service/WorkerService";
import { ActivatedRoute, Router } from "@angular/router";
import {WorkersDetails} from "../../Entity/WorkersDetails";

@Component({
  selector: 'app-worker-details',
  templateUrl: './worker-details.component.html',
  styleUrls: ['./worker-details.component.css']
})
export class WorkerDetailsComponent implements OnInit {

  worker: WorkersDetails | undefined;

  constructor(private service: WorkerService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getWorker(params['uuid'])
        .subscribe(worker => this.worker = worker)
    });
  }

}
