import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Workers} from "../../Entity/Workers";
import {WorkersDetails} from "../../Entity/WorkersDetails";
import {WorkerForm} from "../../Entity/WorkerForm";
import {WorkerEdit} from "../../Entity/WorkerEdit";

@Injectable()
export class WorkerService {

  constructor(private http: HttpClient) {

  }

  getWorkers(): Observable<Workers> {
    return this.http.get<Workers>('/api/workers');
  }

  getWorker(uuid: string): Observable<WorkersDetails> {
    return this.http.get<WorkersDetails>('/api/workers/' + uuid);
  }

  deleteWorker(uuid: string): Observable<any> {
    return this.http.delete('/api/workers/' + uuid);
  }

  postWorker(uuid: string | undefined, request: WorkerForm): Observable<any> {
    return this.http.post('/api/workers?restaurantID=' + uuid, request);
  }

  patchWorker(uuid: string, request: WorkerEdit): Observable<any> {
    return this.http.patch('/api/workers/' + uuid, request);
  }

}
