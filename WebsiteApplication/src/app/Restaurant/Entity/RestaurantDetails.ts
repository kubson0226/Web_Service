import {Workers} from "../../Worker/Entity/Workers";

export interface RestaurantDetails {
  id: string,
  name: string;
  numberOfSits: number;
  workers: Workers;
}
