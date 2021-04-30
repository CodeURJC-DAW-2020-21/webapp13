import { PortfolioitemService } from './../../services/portfolioitem.service';
import { LoginService } from './../../services/login.service';
import { Portfolioitem } from './../../models/portfolioitem.model';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-free-template',
  templateUrl: './free-template.component.html',
  styleUrls: ['./free-template.component.css']
})
export class FreeTemplateComponent implements OnInit {

  user: User

  portfolioItems: Portfolioitem[] = []

  constructor(private loginService:LoginService, private portfolioItemService: PortfolioitemService) { }

  ngOnInit(): void {
    this.user = this.loginService.refreshUser()

    this.portfolioItemService.getPortfolioItems(this.loginService.currentUser().content.id, 0).subscribe(
      portfolioItems => {
        portfolioItems.map(portfolioitem => this.portfolioItems.push(portfolioitem))
        console.log(this.portfolioItems)
      },
      error => console.log("error")
    )

  }

}
