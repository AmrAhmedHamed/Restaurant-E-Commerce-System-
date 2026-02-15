import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  messageAr: string = '';
  messageEn: string = '';
  constructor(private authService: AuthService, private routes: Router) { }

  ngOnInit(): void {
  }


  login(username, password) {
    if(!this.validateAccount(username, password)){
      setTimeout(() => {
        this.messageAr = "";
        this.messageEn = "";
      }, 3000);
      return;
    }

    this.authService.login(username, password).subscribe(
      response => {
        sessionStorage.setItem("token", response.data.token);
        sessionStorage.setItem("roles", response.data.role);
        this.routes.navigateByUrl("/products");
      } , error => {
        // غيري الأسماء هنا
        this.messageAr = error.error.bundleMessage.messageAr;
        this.messageEn = error.error.bundleMessage.messageEn; // ← messageEn بدل message_en
        setTimeout(() => {
          this.messageAr = "";
          this.messageEn = "";
        }, 3000);
      }
    )
  }

  validateAccount(username: string, password: string): boolean {
    if (!username) {
      this.messageAr = "اسم المستخدم مطلوب";
      this.messageEn = "Username is required";
      return false;
    }

    if (!password) {
      this.messageAr = "كلمة المرور مطلوبة";
      this.messageEn = "Password is required";
      return false;
    }


    return true;
  }
}
