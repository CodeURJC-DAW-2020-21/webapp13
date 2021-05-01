import { UserService } from './../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
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

  portfolioItemsMap: any = new Map()
  portfolioItems: Portfolioitem[] = []
  last: boolean = false
  page: number = 0
  pageSize: number = 0
  pageElements: number = 0
  totalElements: number = 0
  actualElements: number = 0
  noItems: boolean = false

  constructor(private userService:UserService,private loginService:LoginService, private portfolioitemService: PortfolioitemService, private router: Router, activatedRoute: ActivatedRoute) {


    const id = activatedRoute.snapshot.params['id'];
    this.userService.getUser(id).subscribe(
      user => {
        this.user = new User(user)
        this.getPortfolioItems(this.page)
        console.log(this.portfolioItems)
      },
      error => {
        this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      }
    )

  }

  ngOnInit(): void {

  }

  loadMore(): void {
    this.getPortfolioItems(this.page)
  }

  private getPortfolioItems(page: number) {
    this.portfolioitemService.getPortfolioItems(this.user.content.id, page).subscribe(
      page => {
        this.totalElements = page["totalElements"]
        if (page["last"]) {
          page["content"].map(item => {
            let newItem = new Portfolioitem(item)
            if (!this.portfolioItemsMap.get(item["id"])) {
              this.portfolioItemsMap.set(item["id"], newItem)
              this.portfolioItems.push(newItem)
              this.actualElements++

              if (page["numberOfElements"] == 3) {
                this.page++
              }
            }
          })
        } else {
          this.actualElements += 3
          this.page++
          page["content"].map(item => {
            let newItem = new Portfolioitem(item)
            this.portfolioItemsMap.set(item["id"], newItem)
            this.portfolioItems.push(newItem)
          })
        }
      },
      error => this.page == 0 ? this.noItems = true : this.router.navigate(['/error'])
    )
  }


}
