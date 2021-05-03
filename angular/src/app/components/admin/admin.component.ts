import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Router } from '@angular/router';
import { Portfolioitem } from '../../models/portfolioitem.model';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {


  usersMap: any = new Map()
  users: User[] = []
  last: boolean = false
  page: number = 0
  pageSize: number = 0
  pageElements: number = 0
  totalElements: number = 0
  actualElements: number = 0
  noItems: boolean = false

  constructor(private userService: UserService, private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    this.getUsers(this.page)
  }

  private getUsers(pageNumber: number): void {
    this.userService.getUsersPage("/api/users/" + "?page=" + pageNumber).subscribe(
      page => {
        this.totalElements = page["totalElements"]
        if (page["last"]) {
          page["content"].map(item => {
            let newItem = new User(item)
            if ((newItem.content.id != this.loginService.currentUser().content.id)) {
              if (!this.usersMap.get(item["id"])) {
                this.usersMap.set(item["id"], newItem)
                this.users.push(newItem)
                this.actualElements++
              }
            }
          })
          this.noItems = true

        } else {

          this.page++
          page["content"].map(item => {
            let newItem = new User(item)
            if ((newItem.content.id != this.loginService.currentUser().content.id)) {
              this.usersMap.set(item["id"], newItem)
              this.users.push(newItem)
              this.actualElements += 1
            }

          })
        }
      },
      error => this.page == 0 ? this.noItems = true : this.router.navigate(['/error'])
    )
  }

  private getUsersPage():void {
    if (this.totalElements == this.actualElements) {
      this.noItems = true
    } else {
      this.userService.getUsersPage("/api/users/" + "?page=" + this.page).subscribe(
        users => {
          users.content.map(user => this.users.push(new User(user)))
          this.actualElements += users.content.length
        },
        error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      )
    }
  }

  private setTotalUsers():void {
    this.userService.getTotalElements("/api/users/" + "?page=" + this.page).subscribe(
      totalElements => this.totalElements = totalElements,
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  loadMore(): void {
    this.getUsers(this.page)
  }

  loadUsers(): void {
    this.setTotalUsers()
    this.deleteDuplicated()
    this.getUsersPage()
  }

  delete(id: string): void {
    this.userService.deleteUser(id).subscribe(
      user => {
        let index = this.users.findIndex(u => u.content["id"] === user.id)
        this.users.splice(index, 1)
        this.loadUsers()
        this.totalElements -= 1
        this.actualElements -= 1
        this.router.navigate(['/'])
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  private deleteDuplicated():void {
    for (var i = this.users.length - 1; i >= 0; i--) {
      if (this.users.indexOf(this.users[i]) !== i) {
        this.users.splice(i, 1)
      }
    }
  }

}
