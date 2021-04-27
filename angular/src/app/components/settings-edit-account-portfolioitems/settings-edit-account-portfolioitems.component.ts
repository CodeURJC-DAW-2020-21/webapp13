import { Component, OnInit } from '@angular/core';
import { PortfolioitemService } from '../../services/portfolioitem.service';

import {DomSanitizer} from '@angular/platform-browser'; 
import { Portfolioitem } from '../../models/portfolioitem.model';

@Component({
  selector: 'app-settings-edit-account-portfolioitems',
  templateUrl: './settings-edit-account-portfolioitems.component.html',
  styleUrls: ['./settings-edit-account-portfolioitems.component.css']
})
export class SettingsEditAccountPortfolioitemsComponent implements OnInit {

  portfolioItems: any[] = []

  constructor(private portfolioitemService: PortfolioitemService) { }

  ngOnInit(): void {
    this.getPortfolioItems("id",0)
  }

  getPortfolioItems(user: string, page:number){
    console.log(user)
    this.portfolioitemService.getPortfolioItems(user,page).subscribe(
      portfolioItems => {
        portfolioItems.map( item => this.portfolioItems.push(new Portfolioitem(item)))
      },
      error => console.log("error")
    )
  }
}
