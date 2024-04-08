import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {map, mergeMap, Observable} from "rxjs";
import { Restaurants } from "../Entity/Restaurants";
import { Restaurant} from "../Entity/Restaurant";
import {RestaurantDetails} from "../Entity/RestaurantDetails";
import {RestaurantForm} from "../Entity/RestaurantForm";
import {Workers} from "../../Worker/Entity/Workers";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) {

  }

  getRestaurants(): Observable<Restaurants> {
    return this.http.get<Restaurants>('api/restaurants');
  }

  getRestaurant(uuid: string): Observable<any> {
    return this.http.get<RestaurantDetails>('api/restaurants/' + uuid).pipe(
      mergeMap((restaurant: RestaurantDetails) => {
        return this.http.get<Workers>('api/workers/restaurant', {params: {restaurantName: restaurant.name}}).pipe(
          map((workers: Workers) => {
            restaurant.workers = workers;
            return restaurant;
          })
        )
      })
    );
  }

  deleteRestaurant(uuid: string): Observable<any> {
    return this.http.delete('api/restaurants/' + uuid);
  }

  patchRestaurant(uuid: string, request: RestaurantForm): Observable<any> {
    return this.http.patch('api/restaurants/' + uuid, request);
  }

  postRestaurant(request: RestaurantForm): Observable<any> {
    return this.http.post('api/restaurants', request);
  }

}
