import { Component, OnInit } from '@angular/core';
import { ChefsService } from '../../../service/chefs.service';
import { Chefs } from '../../../modle/chefs';

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {

  chefsList: Chefs[] = [];

  constructor(private chefsService: ChefsService) {}

  ngOnInit(): void {
    this.getChefs();
  }

  getChefs(): void {
    this.chefsService.getChefs().subscribe(
      value => this.chefsList = value
    );
  }
}
