import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:9090/auth';

  constructor(private http: HttpClient) {}

  createAccount(username: string, password: string, age: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/signup`, { username, password, age }, { observe: 'response' })
      .pipe(
        map(response => {
          return {
            success: true,
            data: response
          };
        }),
        catchError(error => {
          console.error('Signup error:', error);
          return throwError(() => error);
        })
      );
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, { username, password })
      .pipe(
        map(response => {
          return {
            success: true,
            data: response
          };
        }),
        catchError(error => {
          console.error('Login error:', error);
          return throwError(() => error);
        })
      );
  }

  isUserLogin(): boolean {
    return sessionStorage.getItem("token") != null &&
      sessionStorage.getItem("token") != undefined;
  }
  isAdmin(): boolean {
    const roles = sessionStorage.getItem("roles");
    if (!roles) {
      return false;
    }
    return roles.includes("ROLE_ADMIN");
  }

  logOut() {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("roles");
  }
}
