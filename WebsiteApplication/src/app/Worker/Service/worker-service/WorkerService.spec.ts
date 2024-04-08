import { TestBed } from '@angular/core/testing';

import {WorkerService} from "./WorkerService";

describe('WorkerService', () => {
  let service: WorkerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
