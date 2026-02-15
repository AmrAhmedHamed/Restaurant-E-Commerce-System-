import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductDetails } from "../../../../modle/product-details";
import { Product } from "../../../../modle/product";
import { ProductService } from "../../../../service/product.service";
import { ProductDetailsService } from "../../../../service/product-details-service.service";

@Component({
  selector: 'app-add-product-details',
  templateUrl: './add-product-details.component.html',
  styleUrls: ['./add-product-details.component.css']
})
export class AddProductDetailsComponent implements OnInit {
  productDetails: ProductDetails = {
    weight: 0,
    dimensions: '',
    color: '',
    material: '',
    manufacturer: '',
    originCountry: '',
    warrantyPeriod: 0,
    additionalInfo: '',
    productId: 0
  };

  products: Product[] = [];
  selectedProduct: Product | null = null;
  isLoading = false;
  mode: 'add' | 'edit' = 'add';
  productId: number | null = null;

  constructor(
    private productDetailsService: ProductDetailsService,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    console.log('🔍 Component initialized');
    this.loadProducts();

    // تحقق إذا كان في وضع التعديل
    this.route.queryParams.subscribe(params => {
      console.log('Query params:', params);
      if (params['productId']) {
        this.productId = +params['productId'];
        this.productDetails.productId = this.productId;
        console.log('📌 Product ID from params:', this.productId);
      }
    });
  }

  loadProducts(): void {
    console.log('🔄 Loading products...');
    this.productService.getproducties(0, 1000).subscribe(
      (response: any) => {
        console.log('✅ Products API Response:', response);
        if (response && response.content) {
          this.products = response.content;
          console.log('✅ Products loaded:', this.products.length);
          console.log('📋 Products list:', this.products);

          if (this.productId) {
            this.selectedProduct = this.products.find(p => p.id === this.productId) || null;
            if (this.selectedProduct) {
              console.log('✅ Auto-selected product:', this.selectedProduct);
            }
          }
        } else {
          console.error('❌ No content in response');
        }
      },
      (error) => {
        console.error('❌ Error loading products:', error);
        console.error('❌ Error details:', error.error);
        alert('❌ فشل في تحميل المنتجات. الرجاء المحاولة مرة أخرى.');
      }
    );
  }

  onProductSelect(event: any): void {
    const productId = +event.target.value;
    console.log('🎯 Product selected:', productId);

    this.selectedProduct = this.products.find(p => p.id === productId) || null;
    this.productDetails.productId = productId;

    console.log('📌 Selected product:', this.selectedProduct);
  }

  verifyProduct(): void {
    if (!this.productDetails.productId || this.productDetails.productId <= 0) {
      alert('❌ الرجاء إدخال معرف منتج صحيح');
      return;
    }

    this.isLoading = true;

    this.productService.getproducties(0, 1000).subscribe(
      (response: any) => {
        this.products = response.content || [];
        this.selectedProduct = this.products.find(p => p.id === this.productDetails.productId) || null;

        if (this.selectedProduct) {
          console.log('✅ Product verified:', this.selectedProduct);
          alert(`✅ تم العثور على المنتج: ${this.selectedProduct.name}`);

          this.loadExistingDetails(this.productDetails.productId);
        } else {
          alert('❌ لم يتم العثور على منتج بهذا المعرف');
          this.selectedProduct = null;
        }

        this.isLoading = false;
      },
      (error) => {
        console.error('❌ Error verifying product:', error);
        alert('❌ فشل في التحقق من المنتج');
        this.isLoading = false;
      }
    );
  }

  loadExistingDetails(productId: number): void {
    this.isLoading = true;
    this.productDetailsService.getProductDetailsByProductId(productId).subscribe(
      (details: ProductDetails) => {
        this.productDetails = details;
        this.isLoading = false;
        this.mode = 'edit';
        console.log('✅ Existing details loaded:', details);
        alert('✅ تم تحميل التفاصيل الموجودة للتعديل');
      },
      (error) => {
        console.error('❌ Error loading existing details:', error);
        this.isLoading = false;
        this.mode = 'add';
        console.log('ℹ️ No existing details found, staying in add mode');
      }
    );
  }

  saveProductDetails(): void {
    if (!this.productDetails.productId || this.productDetails.productId === 0) {
      alert('❌ الرجاء إدخال معرف المنتج أولاً');
      return;
    }

    if (!this.validateForm()) {
      return;
    }

    this.isLoading = true;

    if (this.mode === 'add') {
      this.productDetailsService.addProductDetails(this.productDetails).subscribe(
        (response: ProductDetails) => {
          console.log('✅ Product details added successfully:', response);
          this.isLoading = false;
          alert('✅ تم إضافة تفاصيل المنتج بنجاح!');
          this.router.navigate(['/products']);
        },
        (error) => {
          console.error('❌ Error adding product details:', error);
          this.isLoading = false;
          this.handleError(error);
        }
      );
    } else {
      this.productDetailsService.updateProductDetails(this.productDetails).subscribe(
        (response: ProductDetails) => {
          console.log('✅ Product details updated successfully:', response);
          this.isLoading = false;
          alert('✅ تم تحديث تفاصيل المنتج بنجاح!');
          this.router.navigate(['/products']);
        },
        (error) => {
          console.error('❌ Error updating product details:', error);
          this.isLoading = false;
          this.handleError(error);
        }
      );
    }
  }

  private validateForm(): boolean {
    if (!this.productDetails.productId || this.productDetails.productId <= 0) {
      alert('❌ الرجاء اختيار منتج');
      return false;
    }

    if (!this.productDetails.weight || this.productDetails.weight <= 0) {
      alert('❌ الوزن يجب أن يكون أكبر من صفر');
      return false;
    }

    if (!this.productDetails.dimensions || this.productDetails.dimensions.trim() === '') {
      alert('❌ الأبعاد مطلوبة');
      return false;
    }

    return true;
  }

  private handleError(error: any): void {
    if (error.error && Array.isArray(error.error)) {
      const errors = error.error.map((err: any) =>
        err.messageAr || err.messageEn
      ).join('\n');
      alert(`❌ Errors:\n${errors}`);
    } else if (error.status === 400) {
      alert('❌ Bad Request - Check your data format');
    } else if (error.status === 404) {
      alert('❌ Product not found');
    } else if (error.status === 409) {
      alert('❌ Product already has details. Use edit instead.');
    } else if (error.status === 500) {
      alert('❌ Server error - Check backend logs');
    } else {
      alert(`❌ Error: ${error.message || 'Unknown error'}`);
    }
  }

  goBack(): void {
    this.router.navigate(['/products']);
  }

  loadTestData(): void {
    this.productDetails = {
      weight: 500,
      dimensions: '20x15x10 cm',
      color: 'أسود',
      material: 'بلاستيك',
      manufacturer: 'شركة الإبداع',
      originCountry: 'مصر',
      warrantyPeriod: 12,
      additionalInfo: 'منتج عالي الجودة مع ضمان لمدة سنة',
      productId: this.productDetails.productId
    };
    alert('✅ Test data loaded!');
  }

  suggestProductIds(): void {
    if (this.products.length > 0) {
      const suggestions = this.products.slice(0, 5).map(p => p.id);
      alert(`جرب هذه المعرفات:\n${suggestions.join(', ')}`);
    } else {
      alert('لم يتم تحميل المنتجات بعد. جرب الأرقام: 1, 2, 3');
    }
  }
}
