import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Router, RouterLink } from '@angular/router';


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

  constructor(private userService: UserService, private router: Router, private loginService:LoginService) { }

  ngOnInit(): void {
    this.getUsers(this.page)
  }

  private getUsers(pageNumber: number) {
    console.log("va bien")
    this.userService.getUsersPage("/api/users/" + "?page=" + pageNumber).subscribe(
      page => {
        this.totalElements = page["totalElements"]
        if (page["last"]) {
          console.log("ultima pagina")
          page["content"].map(item => {
            let newItem = new User(item)
            if((newItem.content.id != this.loginService.currentUser().content.id)){
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
            if((newItem.content.id != this.loginService.currentUser().content.id)){
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

  private getUsersPage() {
    if (this.totalElements == this.actualElements){
      this.noItems = true
    }else{
      this.userService.getUsersPage("/api/users/" + "?page=" + this.page).subscribe(
        users => {
          users.content.map( user => this.users.push(new User(user)))
          //this.deleteDuplicated()
          this.actualElements += users.content.length
          console.log(this.actualElements)
          console.log(users)
        },
        error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      )
    }
  }

  private setTotalUsers() {
    this.userService.getTotalElements("/api/users/" +"?page=" + this.page).subscribe(
      totalElements => {
        this.totalElements = totalElements
        console.log(this.totalElements)
      },
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
        this.router.navigate(['/'])
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
    return this.loginService.isUserAdmin(id)
  }

}
