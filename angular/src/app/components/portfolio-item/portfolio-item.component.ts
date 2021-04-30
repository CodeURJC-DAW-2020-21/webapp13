import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Portfolioitem } from '../../models/portfolioitem.model';
import { LoginService } from '../../services/login.service';
import { PortfolioitemService } from '../../services/portfolioitem.service';

@Component({
  selector: 'app-portfolio-item',
  templateUrl: './portfolio-item.component.html',
  styleUrls: ['./portfolio-item.component.css']
})
export class PortfolioItemComponent implements OnInit {

  id:string
  portfolioItem: Portfolioitem

  constructor(private loginService:LoginService, private portfolioItemService: PortfolioitemService, private activatedRoute: ActivatedRoute) {
    const id = activatedRoute.snapshot.params['id'];
    this.id = id
    this.portfolioItemService.getPortfolioItem(id).subscribe(
      portfolioItem => this.portfolioItem = portfolioItem,
      error => console.log(error)
    )
  }

  ngOnInit(): void {
  }

}
