import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import { ChatService } from '../../services/chat.service'
import { LoginService } from '../../services/login.service';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  
  chats: any = []
  user: string

  constructor(private chatService: ChatService,private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.user = this.loginService.currentUser().content.id
    this.chatService.getChats(this.user).subscribe(
      chats => this.chats = chats,
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

}
