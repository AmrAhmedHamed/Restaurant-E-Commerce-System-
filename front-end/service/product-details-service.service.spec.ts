import { TestBed } from '@angular/core/testing';

import { ProductDetailsServiceService } from './product-details-service.service';

describe('ProductDetailsServiceService', () => {
  let service: ProductDetailsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductDetailsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
