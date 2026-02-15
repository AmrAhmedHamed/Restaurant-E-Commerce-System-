import { Component, OnInit } from '@angular/core';
import {RequestOrderService} from "../../../../service/request-order.service";

@Component({
  selector: 'app-all-users-orders',
  templateUrl: './all-users-orders.component.html',
  styleUrls: ['./all-users-orders.component.css']
})
export class AllUsersOrdersComponent implements OnInit {
  usersOrders: any = null;
  page: number = 1;
  size: number = 10;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(private requestOrderService: RequestOrderService) { }

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.requestOrderService.getAllUsersOrders(this.page, this.size).subscribe(
      value => {
        this.usersOrders = value;
        this.totalPages = value.totalPages;
        this.totalElements = value.totalElements;
      }
    );
  }

  // ⬇️⬇️⬇️ إضافة الدوال مباشرة هنا ⬇️⬇️⬇️
  calculateTotalOrders(): number {
    if (!this.usersOrders || !this.usersOrders.content) return 0;
    return this.usersOrders.content.reduce((sum: number, user: any) => {
      return sum + (user.totalOrders || 0);
    }, 0);
  }

  calculateTotalSpent(): number {
    if (!this.usersOrders || !this.usersOrders.content) return 0;
    return this.usersOrders.content.reduce((sum: number, user: any) => {
      return sum + (user.totalSpent || 0);
    }, 0);
  }

  changePage(newPage: number): void {
    if (newPage >= 1 && newPage <= this.totalPages) {
      this.page = newPage;
      this.loadOrders();
    }
  }

  getPageNumbers(): number[] {
    const pages = [];
    const maxPages = 5;
    let start = Math.max(1, this.page - 2);
    let end = Math.min(this.totalPages, start + maxPages - 1);

    if (end - start + 1 < maxPages) {
      start = Math.max(1, end - maxPages + 1);
    }

    for (let i = start; i <= end; i++) {
      pages.push(i);
    }
    return pages;
  }
}
