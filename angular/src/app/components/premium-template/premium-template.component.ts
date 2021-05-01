import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { PortfolioitemService } from './../../services/portfolioitem.service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { Portfolioitem } from '../../models/portfolioitem.model';

@Component({
  selector: 'app-premium-template',
  templateUrl: './premium-template.component.html',
  styleUrls: ['./premium-template.component.css']
})
export class PremiumTemplateComponent implements OnInit {

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

  canChat: boolean

  constructor(private userService:UserService, private loginService:LoginService, private portfolioItemService: PortfolioitemService, private router: Router,activatedRoute: ActivatedRoute) {
    const id = activatedRoute.snapshot.params['id'];
    this.userService.getUser(id).subscribe(
      user => {
        this.user = new User(user)
        this.getPortfolioItems(this.page)
        console.log(this.portfolioItems)
        const currentUser:User = this.loginService.currentUser()
        console.log(currentUser)
        if (currentUser == undefined){
          this.canChat = false
        }else{
          this.canChat = (this.user.content.id != currentUser.content.id)
        }
        console.log(this.canChat)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  ngOnInit(): void {

  }

  loadMore(): void {
    this.getPortfolioItems(this.page)
  }

  private getPortfolioItems(page: number) {
    this.portfolioItemService.getPortfolioItems(this.user.content.id, page).subscribe(
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
