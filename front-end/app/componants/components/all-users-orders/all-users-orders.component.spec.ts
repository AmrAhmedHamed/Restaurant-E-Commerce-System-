import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllUsersOrdersComponent } from './all-users-orders.component';

describe('AllUsersOrdersComponent', () => {
  let component: AllUsersOrdersComponent;
  let fixture: ComponentFixture<AllUsersOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllUsersOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllUsersOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
