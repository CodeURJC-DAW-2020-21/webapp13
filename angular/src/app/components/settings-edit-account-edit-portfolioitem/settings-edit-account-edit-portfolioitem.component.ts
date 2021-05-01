import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Portfolioitem } from '../../models/portfolioitem.model';
import { PortfolioitemService } from '../../services/portfolioitem.service';


@Component({
  selector: 'app-settings-edit-account-edit-portfolioitem',
  templateUrl: './settings-edit-account-edit-portfolioitem.component.html',
  styleUrls: ['./settings-edit-account-edit-portfolioitem.component.css']
})
export class SettingsEditAccountEditPortfolioitemComponent implements OnInit {

  portfolioItem: Portfolioitem

  constructor(private router: Router,private portfolioitemService: PortfolioitemService, activatedRoute: ActivatedRoute) {
    const id = activatedRoute.snapshot.params['id'];
    this.portfolioitemService.getPortfolioItem(id).subscribe(
      item => this.portfolioItem = new Portfolioitem(item),
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  ngOnInit(): void {

  }

  update(previewImg, image1, image2, image3, name: string, category: string, client: string, date: string, url: string, description: string) {
    console.log(this.portfolioItem.content["userId"])
    console.log(this.portfolioItem.content["id"])

    this.portfolioitemService.putText(this.portfolioItem.content["userId"], this.portfolioItem.content["id"], { name, category, client, date, url, description })
    this.portfolioitemService.putImage(this.portfolioItem.content["userId"], this.portfolioItem.content["id"], "previewImage", previewImg.files[0])
    this.portfolioitemService.putImage(this.portfolioItem.content["userId"], this.portfolioItem.content["id"], "image1", image1.files[0])
    this.portfolioitemService.putImage(this.portfolioItem.content["userId"], this.portfolioItem.content["id"], "image2", image2.files[0])
    this.portfolioitemService.putImage(this.portfolioItem.content["userId"], this.portfolioItem.content["id"], "image3", image3.files[0])






  }

  delete(){
    this.portfolioitemService.delete(this.portfolioItem.content["userId"],this.portfolioItem.content["id"]).subscribe(
      response => this.router.navigate(['/settings-edit-account-portfolioitems']),
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }



}
