import { PortfolioitemService } from './../../services/portfolioitem.service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-premium-template',
  templateUrl: './premium-template.component.html',
  styleUrls: ['./premium-template.component.css']
})
export class PremiumTemplateComponent implements OnInit {

  user: User

  constructor(private loginService:LoginService, private portfolioItemService: PortfolioitemService) { }

  ngOnInit(): void {
    this.user = this.loginService.currentUser()
    console.log(this.user)
  }

}
