import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs"; // أضف of هنا
import { catchError, map, tap } from "rxjs/operators";
import { Product } from "../modle/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl = "http://localhost:9090/ProductController/"

  constructor(private http: HttpClient) { }

  deleteProduct(id: number): Observable<boolean> {
    if (!id) {
      console.error('❌ Cannot delete: ID is null or undefined');
      return of(false);
    }

    const url = `${this.baseUrl}delete/${id}`;

    console.log('🗑️ Deleting product with ID:', id);
    console.log('🚀 DELETE URL:', url);

    return this.http.delete(url, { observe: 'response' }).pipe(
      tap(response => {
        console.log('✅ Delete response status:', response.status);
      }),
      map(response => {
        return response.status === 204;
      }),
      catchError(error => {
        console.error('❌ Error deleting product:', {
          status: error.status,
          message: error.message,
          error: error.error
        });
        return of(false);
      })
    );
  }

  getproducties(page, size): Observable<any> {
    return this.http.get<Product[]>(
      `${this.baseUrl}getAll?pageNumber=${page}&pageSize=${size}`
    ).pipe(
      tap(response => console.log('API Response:', response)),
      map(response => response)
    );
  }

  getproductsByCategoryId(id, page, size): Observable<any> {
    return this.http.get<Product[]>(
      `${this.baseUrl}getByCategory?categoryId=${id}&pageNumber=${page}&pageSize=${size}`
    ).pipe(
      tap(response => console.log('API Response:', response)),
      map(response => response)
    );
  }

  getSearchKey(key, page, size): Observable<any> {
    return this.http.get<any>(
      `${this.baseUrl}search?keyword=${key}&pageNumber=${page}&pageSize=${size}`
    ).pipe(
      tap(response => console.log('Search API Response:', response)),
      map(response => response)
    );
  }addProduct(product: any): Observable<any> {
    const requestData = {
      name: product.name,
      descrip: product.descrip,
      price: Number(product.price),
      imagePath: product.imagePath,
      categoryId: product.categoryId
    };

    const url = `${this.baseUrl}save`;

    console.log('📤 البيانات المرسلة:', requestData);

    return this.http.post<any>(url, requestData).pipe(
      catchError(error => {
        console.error('❌ خطأ:', error);
        throw error;
      })
    );
  }

}
