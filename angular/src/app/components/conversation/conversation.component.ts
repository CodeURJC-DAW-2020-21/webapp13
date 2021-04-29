import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatService } from '../../services/chat.service';
import { LoginService } from '../../services/login.service';

import { Message } from '../../models/message.model'

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  sender: string
  receiver: string
  messages: Message[] = []
  reset:string = ""

  constructor(activatedRoute: ActivatedRoute, public loginService: LoginService, private chatService: ChatService) {
    this.sender = activatedRoute.snapshot.params['id'];
    this.receiver = this.loginService.currentUser().content.id
    this.chatService.getMessages(this.receiver, this.sender).subscribe(
      messages => this.messages = messages,
      error => console.log(error)
    )
  }

  ngOnInit(): void {

  }

  sendMessage(content:string){
    this.chatService.postMessage(this.sender,this.receiver,content).subscribe(
      message => this.messages.push(message),
      error => console.log("error")
    )

    this.reset = ""
  }


  goToBottom(){
    window.scrollTo(0,document.body.scrollHeight);
  }
}
