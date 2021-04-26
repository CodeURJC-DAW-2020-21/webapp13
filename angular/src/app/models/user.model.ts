export class User {
    photo:string
    content: any
    constructor(content){
        this.content = content
        this.photo = "https://localhost:8443/api/users/"+content.id+"/image"
    }
}