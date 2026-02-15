import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class RequestOrderService {

  url = 'http://localhost:9090/api/orders/';

  constructor(private http: HttpClient) { }

  createOrder(productsIds, totalPrice, totalNumber): Observable<any> {
    return this.http.post<any>(this.url + 'create-order' , {productsIds, totalPrice, totalNumber}).pipe(
      map(
        response => response
      )
    );
  }

  getOrder(): Observable<any> {
    return this.http.get<any>(this.url + 'my-orders').pipe(
      map(
        response => response
      )
    );
  }
  getAllUsersOrders(page: number = 1, size: number = 10): Observable<any> {
    return this.http.get<any>(this.url + 'users-history', {
      params: { page: page.toString(), size: size.toString() }
    }).pipe(
      map(response => response)
    );
  }
}
