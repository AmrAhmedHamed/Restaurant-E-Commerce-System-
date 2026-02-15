import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Category } from "../modle/category";
import { Observable } from "rxjs";
import { map, tap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  categoriesUrl: string = "http://localhost:9090/CategoryController/";

  constructor(private http: HttpClient) {}

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(
      `${this.categoriesUrl}getAll`
    ).pipe(
      tap(response => console.log('Category API Response:', response)),
      map(response => response)
    )
  }
}
