export class Portfolioitem{
    previewImg:string
    image1:string
    image2:string
    image3:string
    content: any
    constructor(content){
        this.content = content
        this.previewImg = "/api/portfolioItems/"+content.id+"/previewImage"
        this.image1 = "/api/portfolioItems/"+content.id+"/image1"
        this.image2 = "/api/portfolioItems/"+content.id+"/image2"
        this.image3 = "/api/portfolioItems/"+content.id+"/image3"
    }
}