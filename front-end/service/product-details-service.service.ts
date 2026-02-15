import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductDetails } from "../modle/product-details";

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {
  private baseUrl = 'http://localhost:9090/ProductDetailsController/';

  constructor(private http: HttpClient) {}

  addProductDetails(productDetails: ProductDetails): Observable<ProductDetails> {
    const url = `${this.baseUrl}save`;
    console.log('📤 Adding product details:', productDetails);
    return this.http.post<ProductDetails>(url, productDetails);
  }

  updateProductDetails(productDetails: ProductDetails): Observable<ProductDetails> {
    const url = `${this.baseUrl}update`;
    console.log('✏️ Updating product details:', productDetails);
    return this.http.put<ProductDetails>(url, productDetails);
  }

  getProductDetailsByProductId(productId: number): Observable<ProductDetails> {
    const url = `${this.baseUrl}getByProduct/${productId}`;
    console.log('🔍 Fetching product details for product ID:', productId);
    return this.http.get<ProductDetails>(url);
  }

  getAllProductDetails(): Observable<ProductDetails[]> {
    const url = `${this.baseUrl}getAll`;
    return this.http.get<ProductDetails[]>(url);
  }

  deleteProductDetails(id: number): Observable<void> {
    const url = `${this.baseUrl}delete/${id}`;
    console.log('🗑️ Deleting product details ID:', id);
    return this.http.delete<void>(url);
  }

  deleteProductDetailsByProductId(productId: number): Observable<void> {
    const url = `${this.baseUrl}deleteByProduct/${productId}`;
    console.log('🗑️ Deleting product details for product ID:', productId);
    return this.http.delete<void>(url);
  }
}
