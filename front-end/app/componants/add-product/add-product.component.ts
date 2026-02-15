import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../service/product.service';
import { Router } from '@angular/router';
import { Product } from "../../../modle/product";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  product: any = {
    name: '',
    descrip: '',
    imagePath: '',
    price: 0,
    categoryId: null
  };

  categories = [
    { id: 1, name: 'Breakfast' },
    { id: 2, name: 'Launch' },
    { id: 3, name: 'Dinner' },
    { id: 4, name: 'Ice Cream' },
    { id: 5, name: 'Coffee' },
    { id: 6, name: 'Cake' }
  ];

  isLoading = false;

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  // دالة الاختبار
  testData(): void {
    this.product = {
      name: 'بيتزا مارجريتا',
      descrip: 'بيتزا كلاسيكية مع جبنة موزاريلا وصوص طماطم',
      imagePath: 'foods/pizza.jpg',
      price: 85.99,
      categoryId: 3
    };
    alert('✅ تم تحميل بيانات تجريبية!');
  }

  addProduct(): void {
    console.log('🎯 addProduct() called');

    if (!this.validateProduct()) {
      return;
    }

    this.isLoading = true;


    const productToSend = {
      name: this.product.name,
      descrip: this.product.descrip,
      price: Number(this.product.price),
      imagePath: this.product.imagePath,
      categoryId: this.product.categoryId
    };

    console.log('📤 Final data to send to backend:', productToSend);

    this.productService.addProduct(productToSend).subscribe(
      (response: any) => {
        console.log('✅ Product created successfully:', response);
        this.isLoading = false;
        alert('✅ تم إضافة المنتج بنجاح!');
        this.router.navigate(['/products']);
      },
      (error: any) => {
        console.error(' Error creating product:', error);
        this.isLoading = false;
        this.handleError(error);
      }
    );
  }

  private validateProduct(): boolean {
    console.log('🔍 Validating product...');

    if (!this.product.name || this.product.name.trim() === '') {
      alert('❌ اسم المنتج مطلوب!');
      return false;
    }

    if (!this.product.price || this.product.price <= 0) {
      alert('❌ السعر يجب أن يكون أكبر من صفر!');
      return false;
    }

    if (!this.product.imagePath || this.product.imagePath.trim() === '') {
      alert('❌ مسار الصورة مطلوب!');
      return false;
    }

    if (!this.product.categoryId) {
      alert('❌ يجب اختيار فئة للمنتج!');
      return false;
    }

    console.log('✅ Validation passed');
    return true;
  }

  private handleError(error: any): void {
    let errorMessage = ' فشل في إضافة المنتج';

    if (error.error && Array.isArray(error.error)) {
      // معالجة أخطاء الـ Validation من Spring
      const errors = error.error.map((err: any) =>
        err.messageAr || err.messageEn || err.defaultMessage
      ).join('\n');
      errorMessage = ` الأخطاء:\n${errors}`;
    } else if (error.status === 400) {
      errorMessage = ' بيانات غير صحيحة - تأكد من صيغة البيانات';
    } else if (error.status === 404) {
      errorMessage = ' رابط الخدمة غير موجود';
    } else if (error.status === 409) {
      errorMessage = ' المنتج موجود بالفعل';
    } else if (error.status === 500) {
      errorMessage = ' خطأ في الخادم - تأكد من تشغيل الباكيند';
    } else if (error.error && error.error.message) {
      errorMessage = ` ${error.error.message}`;
    }

    alert(errorMessage);
    console.error('Full error:', error);
  }
}
