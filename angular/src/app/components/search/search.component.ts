import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';


import { User } from '../../models/user.model';

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
  all: boolean = false
  des: boolean = false
  eng: boolean = false
  bus: boolean = false
  pho: boolean = false

  constructor(private router:Router, private userService: UserService, activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getUsersPage()
    this.configButtons('')
    this.setTotalUsers('')
    this.page++
  }

  loadMore(): void {
    this.getUsersPage()
    this.page++
  }

  loadUsers(category: string): void {
    if (category !== this.category) {
      this.users = []
      this.page = 0
      this.totalElements = 0
      this.actualElements = 0
      this.category = category

      this.configButtons(category)
      this.setTotalUsers(category)
    }

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
    this.userService.getUsers("/api/users/" + this.category + "?page=" + this.page).subscribe(
      users => {
        users.map( user => this.users.push(new User(user)))
        this.actualElements += users.length

      },
      error => console.log("error")
    )
  }

  private configButtons(category: string) {
    this.all = false
    this.des = false
    this.eng = false
    this.bus = false
    this.pho = false

    switch (category) {
      case '':
        this.all = true
        break;

      case 'engineers':
        this.eng = true
        break;

      case 'designers':
        this.des = true
        break;

      case 'photographer':
        this.pho = true
        break;

      case 'businessman':
        this.bus = true
        break;

      default:
        break;
    }
  }

  search(user:string): void{
    this.userService.getUser(user).subscribe(
      user => {
        const currentUser: User = new User(user)
        this.userService.getUserActiveTemplate(currentUser.content.id).subscribe(
          template => {
          console.log(template)
            if (template.price==0){
              this.router.navigate(['/free-template', currentUser.content.id])
            }else{
              this.router.navigate(['/premium-template', currentUser.content.id])
            }
          },
          error => console.log("error")
        )
      },
      error => console.log("error")
    )
  }

}
