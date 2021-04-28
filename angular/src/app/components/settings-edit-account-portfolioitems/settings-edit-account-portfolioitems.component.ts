import { Component, OnInit } from '@angular/core';
import { PortfolioitemService } from '../../services/portfolioitem.service';
import { LoginService } from '../../services/login.service';

import { Portfolioitem } from '../../models/portfolioitem.model';

@Component({
  selector: 'app-settings-edit-account-portfolioitems',
  templateUrl: './settings-edit-account-portfolioitems.component.html',
  styleUrls: ['./settings-edit-account-portfolioitems.component.css']
})
export class SettingsEditAccountPortfolioitemsComponent implements OnInit {

  portfolioItemsMap: any = new Map()
  portfolioItems: Portfolioitem[] = []
  user: string = this.loginService.currentUser()["id"]
  last: boolean = false
  page: number = 0
  pageSize: number = 0
  pageElements: number = 0
  totalElements: number = 0
  actualElements: number = 0


  constructor(private portfolioitemService: PortfolioitemService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.getPortfolioItems(this.page)
  }

  loadMore(): void {
    this.getPortfolioItems(this.page)
  }

  create(previewImg, image1, image2, image3, name: string, category: string, client: string, date: string, url: string, description: string) {
    this.portfolioitemService.post({ "userId": "id", name, description, category, client, url, date }).subscribe(
      item => {
        
        this.totalElements++
        
        // Need to try promises
        this.portfolioitemService.put(this.user,item["id"], "previewImage", previewImg.files[0]).subscribe(
          ok => {
            this.portfolioitemService.put(this.user,item["id"], "image1", image1.files[0]).subscribe(
              ok => {
                this.portfolioitemService.put(this.user,item["id"], "image2", image2.files[0]).subscribe(
                  ok => {
                    this.portfolioitemService.put(this.user,item["id"], "image3", image3.files[0]).subscribe(
                      ok => {
                          console.log("ok")
                      },
                      error => console.log(error)
                    )
                  },
                  error => console.log(error)
                )
              },
              error => console.log(error)
            )
          },
          error => console.log(error)
        )


      },
      error => console.log("Error")
    )
    

  }

  private getPortfolioItems(page: number) {
    this.portfolioitemService.getPortfolioItems(this.user, page).subscribe(
      page => {
        this.totalElements = page["totalElements"]
        if (page["last"]){
          page["content"].map( item => {
            let newItem = new Portfolioitem(item)
            if (!this.portfolioItemsMap.get(item["id"])){
              this.portfolioItemsMap.set(item["id"],newItem)
              this.portfolioItems.push(newItem)
              this.actualElements++

              if (page["numberOfElements"] == 3){
                this.page++
              }
            }
          })
        } else {
          this.actualElements += 3
          this.page++
          page["content"].map( item =>{
            let newItem = new Portfolioitem(item)
            this.portfolioItemsMap.set(item["id"],newItem)
            this.portfolioItems.push(newItem)
          })
        }
      },
      error => console.log("error")
    )
  }
}
