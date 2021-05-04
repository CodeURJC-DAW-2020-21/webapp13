export class User {
    photo:string
    content: any
    constructor(content){
        this.content = content
        if (content == "userNotLogued"){
          this.photo = "../../assets/images/default-profile.jpg"
        }else{
          this.photo = "/api/users/"+content.id+"/image"
        }
    }

    getId():string{
        return this.content.id
    }
}
