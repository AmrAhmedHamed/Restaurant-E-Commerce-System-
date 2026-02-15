import { Component } from '@angular/core';
import { ProductOrder } from "../../../modle/product-order";
import { CartService } from "../../../service/cart.service";
import { Router } from "@angular/router";
import { RequestOrderService } from "../../../service/request-order.service";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent {
  productOrders: ProductOrder[] = [];
  totalProductSize: number = 0;
  totalProductPrice: number = 0;
  userId: number = 1;

  constructor(
    private cartService: CartService,
    private router: Router,
    private requestOrderService: RequestOrderService
  ) {}

  ngOnInit(): void {
    this.productOrders = this.cartService.productOrders;
    this.calculateTotals();
  }

  // Add this method to calculate totals
  calculateTotals() {
    this.totalProductSize = this.productOrders.reduce((total, order) => total + order.quantity, 0);
    this.totalProductPrice = this.productOrders.reduce((total, order) => total + (order.quantity * order.price), 0);
  }

  addProduct(productOrder: ProductOrder) {
    this.cartService.addProductToOrder(productOrder);
    this.calculateTotals(); // Update totals after adding
  }

  removeSelectedProduct(productOrder: ProductOrder) {
    this.cartService.removeProduct(productOrder);
    this.calculateTotals(); // Update totals after removing
  }

  removeFullProduct(productOrder: ProductOrder) {
    this.cartService.remove(productOrder);
    this.productOrders = this.cartService.productOrders; // Update local array
    this.calculateTotals(); // Update totals after removal
  }

  createOrder() {
    if (this.productOrders.length === 0) {
      alert('No products in cart!');
      return;
    }

    const productIds = this.productOrders.map(or => or.id);

    console.log('Sending order data:', {
      productsIds: productIds,
      totalPrice: this.totalProductPrice,
      totalNumber: this.totalProductSize,
      userId: this.userId
    });

    this.requestOrderService.createOrder(
      productIds,
      this.totalProductPrice,
      this.totalProductSize,
    ).subscribe(
      response => {
        console.log('Order created successfully:', response);
        this.cartService.productOrders = [];
        this.cartService.totalPrice.next(0);
        this.cartService.totalOrderSize.next(0);
        this.router.navigateByUrl("/order-code/" + response.code);
      },
      error => {
        console.error('Full error details:', error);
        // Get more detailed error message
        const errorMsg = error.error?.message || error.message || 'Unknown error';
        alert('Error creating order: ' + errorMsg);
      }
    );
  }
}
