import { Component, OnInit } from '@angular/core';
import {RestaurantService} from "../../Service/restaurant.service";
import { ActivatedRoute, Router } from "@angular/router";
import {RestaurantDetails} from "../../Entity/RestaurantDetails";

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  restaurant: RestaurantDetails | undefined;
  uuid: string |undefined;

  constructor(private service: RestaurantService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getRestaurant(params['uuid'])
        .subscribe(restaurant => this.restaurant = restaurant)
    });
  }

}
