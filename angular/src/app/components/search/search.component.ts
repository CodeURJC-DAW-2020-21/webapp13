import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  users: any[] = []
  page: number = 0
  totalElements: number = 0
  actualElements: number = 0
  category: string = ""

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {

    // Get first page of users and update the number of visible users
    this.userService.getUsers("/api/users/?page=0").subscribe(
      users => {
        this.users = users
        this.actualElements += this.users.length
      },
      error => console.log("error getting users")
    )

    // Set total users in the app
    this.setTotalUsers('')

    this.page++
  }

  loadMore(): void {
    // Get next page of users and update the number of visible users
    this.userService.getUsers("/api/users/"+this.category+"?page=" + this.page).subscribe(
      users => {
        users.map(user => this.users.push(user))
        this.actualElements += users.length

      },
      error => console.log("error")
    )

    this.page++
  }

  loadUsers(category: string): void {
    // Set empty if we want to show other category
    if (category !== this.category) {
      this.users = []
      this.page = 0
      this.totalElements = 0
      this.actualElements = 0
      this.category = category

      // Set total users in the app
      this.setTotalUsers(category)
    }

    // Get first page of users and update the number of visible users
    this.getUsersPage()
    
    this.page++
  }

  private setTotalUsers(category: string) {
    this.userService.getTotalElements("/api/users/" + category + "?page=" + this.page).subscribe(
      totalElements => this.totalElements = totalElements,
      error => console.log("error getting total elements")
    )
  }

  private getUsersPage() {
    this.userService.getUsers("/api/users/"+this.category+"?page=" + this.page).subscribe(
      users => {
        users.map(user => this.users.push(user))
        this.actualElements += users.length

      },
      error => console.log("error")
    )
  }
}
