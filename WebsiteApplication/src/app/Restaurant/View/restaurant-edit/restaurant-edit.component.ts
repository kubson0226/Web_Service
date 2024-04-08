import { Component, OnInit } from '@angular/core';
import {RestaurantService} from "../../Service/restaurant.service";
import { ActivatedRoute, Router } from '@angular/router';
import {RestaurantForm} from "../../Entity/RestaurantForm";
import {Restaurants} from "../../Entity/Restaurants";

@Component({
  selector: 'app-restaurant-edit',
  templateUrl: './restaurant-edit.component.html',
  styleUrls: ['./restaurant-edit.component.css']
})
export class RestaurantEditComponent implements OnInit {

  uuid: string | undefined;

  restaurant: RestaurantForm | undefined;

  original: RestaurantForm | undefined;

  restaurants: Restaurants | undefined;

  constructor(
    private restaurantService: RestaurantService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
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
    });
  }

  onSubmit(): void {
    this.restaurantService.patchRestaurant(this.uuid!, this.restaurant!)
      .subscribe(() => this.router.navigate(['/restaurants', this.uuid]));
  }

}
