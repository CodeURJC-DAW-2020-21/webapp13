var page = 1
var count = 0

count = $(".user-item").length
$("#total").html(count)

function cargarMasS() {

    $("#result").append($("<div class='row' id='users-to-load'>").load("/search/?page=" + (page) + "&size=8 .user-item", function () {
        count = $(".user-item").length
        $("#total").html(count)
        $("#hasNextPage").load("/search/?page=" + page + "&size=8 #button-replace", function () {
            $("#load-more").click(cargarMasS)
            page++
        })
    }))
}

function cargarMasP() {

    $("#result").append($("<div class='row' id='users-to-load'>").load("/settings/edit/account/portfolioitems/?page=" + (page) + "&size=3 .portfolioitem-item", function () {
        $("#hasNextPage").load("/settings/edit/account/portfolioitems/?page=" + (page) + "&size=3 #button-replace", function () {
            $("#load-more1").click(cargarMasP)
            page++
        })
    }))
}

$("#load-more").click(cargarMasS)

$("#load-more1").click(cargarMasP)






