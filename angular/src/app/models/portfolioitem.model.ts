export class Portfolioitem{
    previewImg:string
    image1:string
    image2:string
    image3:string
    content: any
    constructor(content){
        this.content = content
        this.previewImg = "https://localhost:8443/api/portfolioItems/"+content.id+"/previewImage"
        this.image1 = "https://localhost:8443/api/portfolioItems/"+content.id+"/image1"
        this.image2 = "https://localhost:8443/api/portfolioItems/"+content.id+"/image2"
        this.image3 = "https://localhost:8443/api/portfolioItems/"+content.id+"/image3"
    }
}