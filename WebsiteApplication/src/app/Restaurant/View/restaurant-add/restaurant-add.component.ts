// city-add.component.ts
import {Component, OnInit} from '@angular/core';
import {RestaurantService} from "../../Service/restaurant.service";
import {Router} from '@angular/router';
import {RestaurantForm} from "../../Entity/RestaurantForm";

@Component({
  selector: 'app-restaurant-add',
  templateUrl: './restaurant-add.component.html',
  styleUrls: ['./restaurant-add.component.css']
})
export class RestaurantAddComponent implements OnInit {

  restaurant: RestaurantForm = {
    name: '',
    numberOfSits: 0
  };

  constructor(
    private restaurantService: RestaurantService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  onSubmit(): void {
    this.restaurantService.postRestaurant(this.restaurant)
      .subscribe(() => this.router.navigate(['/restaurants']));
  }

}
