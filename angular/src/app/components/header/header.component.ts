import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service'
import { UserService } from '../../services/user.service'

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public loginService: LoginService, private userService: UserService) { }

  ngOnInit(): void {
  }

  /*logOut() {
    this.loginService.logOut();
    console.log("Logging out")
  }*/

  logOut() {
    this.loginService.logOut().subscribe(
      response => console.log("Logging out")
    );
    
  }

  imLogged(){
    //console.log(this.loginService.isLogged())
    return this.loginService.isLogged();
  }

  search(user:string): void{
    this.userService.getUser(user).subscribe(
      user => console.log(user),
      error => console.log("error")
    )
  }

}
