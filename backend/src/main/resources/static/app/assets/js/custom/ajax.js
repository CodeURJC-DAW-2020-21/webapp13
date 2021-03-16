var page = 1
var count = 0

count = $(".user-item").length
$("#total").html(count)
function cargarMas() {

    $("#result").append($("<div class='row' id='users-to-load'>").load("/search/?page=" + (page) + "&size=8 .user-item", function () {
        count = $(".user-item").length
        $("#total").html(count)
        $("#hasNextPage").load("/search/?page=" + page + "&size=8 #button-replace", function () {
            $("#load-more").click(cargarMas)
            page++
        })
    }))
}

$("#load-more").click(cargarMas)




