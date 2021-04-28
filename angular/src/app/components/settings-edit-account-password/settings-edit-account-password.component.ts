import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-settings-edit-account-password',
  templateUrl: './settings-edit-account-password.component.html',
  styleUrls: ['./settings-edit-account-password.component.css']
})
export class SettingsEditAccountPasswordComponent implements OnInit {

  constructor(private userService: UserService, private loginService:LoginService) { }

  user: User;

  ngOnInit(): void {
    this.user = this.loginService.currentUser()
  }

  update(password1:string,password2:string){
    if (password1 == password2){
      this.userService.putPassword(this.user.content.id,password1).subscribe(
        user => {
          console.log(user)
        },
        error => console.log("error")
      )
    }else{
      console.log("error")
    }

  }





}
