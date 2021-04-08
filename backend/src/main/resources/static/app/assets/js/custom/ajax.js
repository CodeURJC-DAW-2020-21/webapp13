var page = 1
var count = 0

count = $(".user-item").length
$("#total").html(count)

function loadMoreS() {

    $("#result").append($("<div class='row' id='users-to-load'>").load("/search/?page=" + (page) + "&size=8 .user-item", function () {
        count = $(".user-item").length
        $("#total").html(count)
        $("#hasNextPage").load("/search/?page=" + page + "&size=8 #button-replace", function () {
            $("#load-more").click(loadMoreS)
            page++
        })
    }))
}


function loadMoreP() {

    $("#result").append($("<div class='row' id='users-to-load'>").load("/settings/edit/account/portfolioitems/?page=" + (page) + "&size=3 .portfolioitem-item", function () {
        $("#hasNextPage").load("/settings/edit/account/portfolioitems/?page=" + (page) + "&size=3 #button-replace", function () {
            $("#load-more1").click(loadMoreP)
            page++
        })
    }))
}


function loadMoreT() {
    console.log($("#user-id").text().trim())
    $("#result").append($("<div class='row no-gutters w-100 h-100' id='users-to-load'>").load("/template/" + $("#user-id").text().trim() + "/?page=" + (page) + "&size=3 .portfolioitem-item", function () {
        $("#hasNextPage").load("/template/" + $("#user-id").text().trim()  + "/?page=" + (page) + "&size=3 #button-replace", function () {
            $("#load-more2").click(loadMoreT)
            page++
        })
    }))
}


$("#load-more").click(loadMoreS)

$("#load-more1").click(loadMoreP)

$("#load-more2").click(loadMoreT)






