export class Template{
    id: number
    htmlPath: string
    name: string
    price: number
    isFree: boolean
    description:string

    constructor(id: number, htmlPath: string, name: string, price: number, isFree: boolean, description: string){
        this.id = id
        this.htmlPath = htmlPath
        this.name = name
        this.price = price
        this.isFree = isFree
        this.description = description
    }
}