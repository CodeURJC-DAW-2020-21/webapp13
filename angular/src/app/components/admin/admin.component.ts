import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: any[] = []
  page: number = 0
  totalElements: number = 0
  actualElements: number = 0

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getUsersPage()
    this.setTotalUsers()
    this.page++
  }

  loadMore(): void {
    this.getUsersPage() 
    this.page++
  }

  loadUsers(): void {
    this.setTotalUsers()
    this.getUsersPage()
    this.page++
  }

  private setTotalUsers() {
    this.userService.getTotalElements("/api/users/" +"?page=" + this.page).subscribe(
      totalElements => this.totalElements = totalElements,
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  private getUsersPage() {
    this.userService.getUsers("/api/users/" + "?page=" + this.page).subscribe(
      users => {
        users.map( user => this.users.push(new User(user)))
        this.actualElements += users.length

      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  delete(id:string){
    return this.userService.deleteUser(id).subscribe(
      user => {
        console.log(user)
        user = this.userService.getUser(id)
        let index = this.users.indexOf(user)
        this.users.splice(index, 1)
        this.actualElements -= 1
        this.getUsersPage()
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

}
