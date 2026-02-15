import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Chefs} from "../modle/chefs";


@Injectable({
  providedIn: 'root'
})
export class ChefsService {

  private chefsUrl: string = 'http://localhost:9090/ChefController/getAllChefs';

  constructor(private http: HttpClient) { }

  getChefs(): Observable<Chefs[]> {
    return this.http.get<Chefs[]>(this.chefsUrl).pipe(
      map(response => response)
    );
  }
}
