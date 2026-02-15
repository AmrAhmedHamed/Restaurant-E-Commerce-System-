import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {ProductsComponent} from './componants/products/products.component';
import {HeaderComponent} from './componants/header/header.component';
import {CategoryComponent} from './componants/category/category.component';
import {CardDetailsComponent} from './componants/card-details/card-details.component';
import {CardComponent} from './componants/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {FooterComponent} from './componants/footer/footer.component';
import { ChefsComponent } from './componants/chefs/chefs.component';
import { ContactInfoComponent } from './componants/contact-info/contact-info.component';
import {APP_BASE_HREF} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './componants/login/login.component';
import { SignupComponent } from './componants/signup/signup.component';
import {AuthInterceptor} from "../interceptor/core/auth.interceptor";
import {AuthGuard} from "../guard/auth.guard";
import {LoginSignUpGuardGuard} from "../guard/login-sign-up-guard.guard";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import { OrderCodeComponent } from './componants/order-code/order-code.component';
import { OrderUserComponent } from './componants/order-user/order-user.component';
import { AllUsersOrdersComponent } from './componants/components/all-users-orders/all-users-orders.component';
import { AddProductComponent } from './componants/add-product/add-product.component';
import { FormsModule } from '@angular/forms';
import { AddProductDetailsComponent } from './componants/components/add-product-details/add-product-details.component';

// http://localhost:4200/
export const routes: Routes = [

  // http://localhost:4200/active
  {path: 'products', component: ProductsComponent,canActivate:[AuthGuard]},
  {path: 'category/:id', component: ProductsComponent,canActivate:[AuthGuard]},
  {path: 'products/:key', component: ProductsComponent,canActivate:[AuthGuard]},
  {path: 'cardDetails', component: CardDetailsComponent,canActivate:[AuthGuard]},
  {path: 'contact-info', component: ContactInfoComponent,canActivate:[AuthGuard]},
  {path: 'login', component: LoginComponent,canActivate:[LoginSignUpGuardGuard]},
  {path: 'signup', component: SignupComponent,canActivate:[LoginSignUpGuardGuard]},
  {path: 'chefs', component: ChefsComponent,canActivate:[AuthGuard]},
  { path: 'order-code/:code', component: OrderCodeComponent },
{path: 'orders-user', component: OrderUserComponent, canActivate:[AuthGuard]},
  { path: 'admin/orders-history', component: AllUsersOrdersComponent, canActivate: [AuthGuard] },
  { path: 'add-product', component: AddProductComponent, canActivate: [AuthGuard] },
  { path: 'add-product-details', component: AddProductDetailsComponent, canActivate: [AuthGuard] },
  { path: 'edit-product-details/:productId', component: AddProductDetailsComponent, canActivate: [AuthGuard] },




// http://localhost:4200/
  {path: '', redirectTo: '/products', pathMatch: 'full'},

  // if user enter thing without all routes
  {path: '**', redirectTo: '/products', pathMatch: 'full'}

];

/*
*   // http://localhost:4200/
  {path: '', component:OrderItemsComponent}
* */
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    CategoryComponent,
    CardDetailsComponent,
    CardComponent,
    FooterComponent,
    ChefsComponent,
    ContactInfoComponent,
    LoginComponent,
    SignupComponent,
    OrderCodeComponent,
    OrderUserComponent,
    AllUsersOrdersComponent,
    AddProductComponent,
    AddProductDetailsComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbPaginationModule,
    FormsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  { provide: APP_BASE_HREF, useValue: '/' }],
  bootstrap: [
  AppComponent
  ]
})
export class AppModule { }
