import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from "../../../modle/product";
import { ProductService } from "../../../service/product.service";
import { ActivatedRoute, Router } from "@angular/router";
import { CartService } from "../../../service/cart.service";
import { ProductOrder } from "../../../modle/product-order";
import { AuthService } from "../../../service/auth.service";
import { ProductDetails } from "../../../modle/product-details";
import { ProductDetailsService } from "../../../service/product-details-service.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  @ViewChild('productDetailsModal') productDetailsModal!: TemplateRef<any>;

  protects: Product[] = [];
  messageAr: string = '';
  messageEn: string = '';
  pageNumber: number = 1;
  pageSize: number = 20;
  totalProductSize: number = 0;

  selectedProductDetails: ProductDetails | null = null;
  selectedProductForModal: Product | null = null;

  constructor(
    private productService: ProductService,
    private productDetailsService: ProductDetailsService,
    private activatedRoute: ActivatedRoute,
    private cartService: CartService,
    private authService: AuthService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(() => this.getAllProduct(this.pageNumber));
  }

  getAllProduct(pageNum: number) {
    let hasCategoryId = this.activatedRoute.snapshot.paramMap.has("id");
    let getSearch = this.activatedRoute.snapshot.paramMap.has("key");

    if (hasCategoryId) {
      let categoryId = this.activatedRoute.snapshot.paramMap.get("id");
      this.getProductsByCategoriesID(categoryId, pageNum);
    } else if (getSearch) {
      let searchKey = this.activatedRoute.snapshot.paramMap.get("key");
      this.getSearch(searchKey, pageNum);
    } else {
      this.getProducts(pageNum);
    }
  }

  getProducts(pageNum: number) {
    return this.productService.getproducties(pageNum, this.pageSize).subscribe(
      response => {
        this.protects = response.content;
        this.totalProductSize = response.totalElements;
      }
    );
  }

  getProductsByCategoriesID(id: string, pageNum: number) {
    return this.productService.getproductsByCategoryId(id, pageNum, this.pageSize).subscribe(
      response => {
        this.protects = response.content;
        this.totalProductSize = response.totalElements;
      }
    );
  }

  getSearch(key: string, pageNum: number) {
    return this.productService.getSearchKey(key, pageNum, this.pageSize).subscribe(
      response => {
        console.log('Search Response:', response);
        this.protects = response.content;
        this.totalProductSize = response.totalElements;
      },
      error => {
        this.messageAr = error.error.bundleMessage.message_ar;
        this.messageEn = error.error.bundleMessage.message_en;
        this.protects = [];
        this.totalProductSize = 0;
      }
    );
  }

  pagination() {
    this.getAllProduct(this.pageNumber);
  }

  changePageSize(event: Event) {
    this.pageSize = +(<HTMLInputElement>event.target).value;
    this.getAllProduct(this.pageNumber);
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  addProduct(product: Product) {
    let productOrder = new ProductOrder(product);
    this.cartService.addProductToOrder(productOrder);
  }

  showProductDetails(product: Product): void {
    this.selectedProductForModal = product;

    this.productDetailsService.getProductDetailsByProductId(product.id).subscribe(
      (details: ProductDetails) => {
        this.selectedProductDetails = details;
        // فتح الـ modal
        this.modalService.open(this.productDetailsModal, { size: 'lg', centered: true });
      },
      (error) => {
        console.log('No details found for product:', product.id);
        this.selectedProductDetails = null;
        this.modalService.open(this.productDetailsModal, { size: 'lg', centered: true });
      }
    );
  }

  deleteProduct(product: Product): void {
    if (!product || !product.id) {
      console.error('❌ Cannot delete: Product or product ID is null');
      alert('❌ Cannot delete product: Invalid product data');
      return;
    }

    const isConfirmed = confirm(`هل أنت متأكد من حذف "${product.name}"؟`);

    if (!isConfirmed) {
      return;
    }

    console.log(`🗑️ Attempting to delete product: ${product.name} (ID: ${product.id})`);

    this.productService.deleteProduct(product.id).subscribe(
      (success: boolean) => {
        if (success) {
          console.log('✅ Product deleted successfully');
          alert('✅ تم حذف المنتج بنجاح!');

          this.protects = this.protects.filter(p => p.id !== product.id);
          this.totalProductSize--;

          if (this.protects.length === 0 && this.pageNumber > 1) {
            this.pageNumber--;
            this.getAllProduct(this.pageNumber);
          }
        } else {
          console.error('❌ Failed to delete product');
          alert('❌ فشل في حذف المنتج. الرجاء المحاولة مرة أخرى.');
        }
      },
      (error) => {
        console.error('❌ Error deleting product:', error);
        alert(`❌ خطأ في حذف المنتج: ${error.message || 'خطأ غير معروف'}`);
      }
    );
  }

  editProduct(product: Product): void {
    console.log('✏️ Editing product:', product);
    if (product && product.id) {
      this.router.navigate(['/edit-product', product.id]);
    } else {
      alert('❌ Cannot edit: Invalid product data');
    }
  }

  manageProductDetails(product: Product): void {
    console.log('🔧 Managing product details for:', product);
    if (product && product.id) {
      this.productDetailsService.getProductDetailsByProductId(product.id).subscribe(
        (details: ProductDetails) => {
          this.router.navigate(['/edit-product-details', product.id]);
        },
        (error) => {
          this.router.navigate(['/add-product-details'], { queryParams: { productId: product.id } });
        }
      );
    }
  }
}
