import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings-edit-account-password',
  templateUrl: './settings-edit-account-password.component.html',
  styleUrls: ['./settings-edit-account-password.component.css']
})
export class SettingsEditAccountPasswordComponent implements OnInit {

  constructor(private userService: UserService, private loginService:LoginService, private router: Router) { }

  user: User;

  ngOnInit(): void {
    this.getUser("id", 0)
  }

  getUser(userId: string, page:number){
    this.userService.getUser(userId).subscribe(
      item => this.user = new User(item),
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  update(password1:string,password2:string){
    if (password1 == password2){
      this.userService.putPassword(this.user.content.id,password1).subscribe(
        user => {
        },
        error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      )
    }else{
      alert("Las contraseñas no coinciden")
    }

  }





}
