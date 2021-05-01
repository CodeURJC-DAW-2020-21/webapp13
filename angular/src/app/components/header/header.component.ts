import { Template } from './../../models/template.model';
import { TemplateService } from './../../services/template.service';
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

  constructor(private router:Router, public loginService: LoginService, private userService: UserService, private templateService: TemplateService) { }

  ngOnInit(): void {}

  logOut() {
    this.loginService.logOut().subscribe(
      response => console.log("Logging out")
    );

  }

  imLogged(): boolean{
    return this.loginService.isLogged();
  }

  imAdmin(){
    return this.loginService.checkAdmin();
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
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  goToActiveTemplate(){
    const currentUser: User = this.loginService.currentUser()
    this.userService.getUserActiveTemplate(currentUser.content.id).subscribe(
      template => {
        console.log(template)
        if (template.price==0){
          this.router.navigate(['/free-template', currentUser.content.id])
        }else{
          this.router.navigate(['/premium-template', currentUser.content.id])
        }
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }


}
