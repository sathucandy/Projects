import { Injectable } from "@angular/core";
import { CartItem } from "../common/cart-item";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CartService {
  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() {}

  addToCart(theCartItem: CartItem) {
    // check if we already have the item in our cart
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    if (this.cartItems.length > 0) {
      // find the item in the cart based on item id

      for (let tempCartItem of this.cartItems) {
        if (tempCartItem.id === theCartItem.id) {
          existingCartItem = tempCartItem;
          break;
        }
      }
      // check if we found it
      alreadyExistsInCart = existingCartItem != undefined;
    }
    if (alreadyExistsInCart) {
      existingCartItem.quantity++;
    } else {
      this.cartItems.push(theCartItem);
    }

    // compute the cart totla price and total quantity
    this.computeCartTotals();
  }
  computeCartTotals() {
    throw new Error("Method not implemented.");
  }
}
