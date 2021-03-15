var page = 1

$("#load-more").click(function(){
    $("#result").append($('<div class="row" id="users-to-load">').load("/search/?page="+(page++)+"&size=8 .user-item"))
    count = $(".user-item").length;
    $("#total").html(count)
})

while(1){

    console.log(count)
}





