import {Component, OnInit} from '@angular/core';
import {RestaurantService} from "../../Service/restaurant.service";
import {Restaurants} from "../../Entity/Restaurants";
import {Restaurant} from "../../Entity/Restaurant";

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrl: './restaurant-list.component.css'
})
export class RestaurantListComponent implements OnInit{

  restaurants: Restaurants | undefined;

  constructor(private service: RestaurantService) {

  }

  ngOnInit() {
    this.service.getRestaurants().subscribe(restaurants => {
      return this.restaurants = restaurants;
    });
  }

  onDelete(restaurant: Restaurant): void {
    this.service.deleteRestaurant(restaurant.id).subscribe(() => this.ngOnInit());
  }
}
