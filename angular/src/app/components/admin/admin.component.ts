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
    this.setTotalUsers()
    this.getUsersPage()
    this.page++
  }

  loadMore(): void {
    if (this.actualElements !== (this.page + 1) * 8) {
      this.page--
      this.getUsersPage()
      this.page++
    }
    this.getUsersPage()
    this.page++
  }

  loadUsers(): void {
    this.setTotalUsers()
    this.deleteDuplicated()
    this.getUsersPage()
  }

  private setTotalUsers() {
    this.userService.getTotalElements("/api/users/" +"?page=" + this.page).subscribe(
      totalElements => {
        this.totalElements = totalElements
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  private getUsersPage() {
    this.userService.getUsersPage("/api/users/" + "?page=" + this.page).subscribe(
      users => {
        users.content.map( user => this.users.push(new User(user)))
        this.deleteDuplicated()
        this.actualElements += users.content.length
        console.log(this.actualElements)
        console.log(users)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  delete(id:string){
    return this.userService.deleteUser(id).subscribe(
      user => {
        let index = this.users.findIndex(u => u.content["id"] === user.id)
        this.users.splice(index, 1)
        this.loadUsers()
        console.log(this.totalElements)
        this.totalElements -= 1
        this.actualElements -= 1
        console.log(this.totalElements)
        console.log("Elementos actuales:", this.actualElements)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  private deleteDuplicated(){
    for(var i = this.users.length -1; i >=0; i--){
      if(this.users.indexOf(this.users[i]) !== i){
        this.users.splice(i,1)
        console.log("He borrado un duplicado")

      }
    }
  }
  isAdmin(id:string){
    return id="admin"
  }

}
