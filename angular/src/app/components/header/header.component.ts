import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
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
    return this.loginService.isLogged();
  }

  imAdmin(){
    return this.loginService.checkAdmin();
  }

  search(user:string): void{
    this.userService.getUser(user).subscribe(
      user => console.log(user),
      error => console.log("error")
    )
  }

}
