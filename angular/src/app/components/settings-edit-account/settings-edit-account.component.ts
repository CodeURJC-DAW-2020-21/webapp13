import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-settings-edit-account',
  templateUrl: './settings-edit-account.component.html',
  styleUrls: ['./settings-edit-account.component.css']
})
export class SettingsEditAccountComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService, private loginService:LoginService) { }

  ngOnInit(): void {
    //this.user = this.loginService.currentUser()
    this.getUser("id", 0)
  }

  getUser(userId: string, page:number){
    console.log(userId)
    this.userService.getUser(userId).subscribe(
      item => this.user = new User(item),
      error => console.log("error")
    )
    console.log(this.user.content.name)
  }

  update(photo,id:string,name:string,surname:string,email:string,phoneNumber:string,city:string,degree:string,freelance:string,category:string,description:string){
    this.userService.putUser(id,name,surname,email,phoneNumber,city,degree,freelance,category,description).subscribe(
      user => {
        console.log(user)
        error => console.log(error)
        this.userService.putImage(this.user.getId(), "profilePhoto", photo.files[0]).subscribe(
          ok => {
              console.log("ok")
          },
          error => console.log(error)
        )
      },
      error => console.log("error")
    )
  }

}
