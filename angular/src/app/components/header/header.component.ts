import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import {LoginService} from '../../services/login.service'
import { UserService } from '../../services/user.service'

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router, public loginService: LoginService, private userService: UserService) { }

 

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
      user => {
        if(user.template.name === "Free"){
          this.router.navigate(['/free-template',user.id])
        } else {
          this.router.navigate(['/premiun-template',user.id])
        }
        
      } ,
      error => console.log("error")
    )
  }


}
