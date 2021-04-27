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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUser("id",0)
  }

  getUser(userId: string, page:number){
    console.log(userId)
    this.userService.getUser(userId).subscribe(
      item => this.user = new User(item),
      error => console.log("error")
    )
    console.log(this.user.content.name)
  }

}
