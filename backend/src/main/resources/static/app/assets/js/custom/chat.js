var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            console.log("Mensaje recibido")
            showGreeting(JSON.parse(greeting.body).sender, JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName() {
    let path = "/app/chat" //+$("#receiver-id").text()
    stompClient.send(path, {}, JSON.stringify({
        'content': $("#name").val(),
        'sender': $("#user-id").text(),
        'receiver': $("#receiver-id").text()
    }));
}

function showGreeting(sender, message) {
    if (sender === $("#user-id").text())
        $("#conversation").append("<tr><td id='message' class='text-right'>" + message + " : <b class='sender-id'>" + sender + "</b></td></tr>");
    else
        $("#conversation").append("<tr><td id='message' class='text-left'> <b class='sender-id'>" + sender + "</b>" + " : " + message + "</td></tr>");

}

function organize() {
    let messages = $(".message")

    let sender = $(".sender-id ").map(function () {
        return this.innerText
    })
    for (let i = 0; i < messages.length; i++) {
        if (sender[i] === $("#user-id").text()) {
            messages.eq(i).addClass("text-right")
            let arr = messages.eq(i).text().split(" ")
            messages.eq(i).text("")
            let content = arr.toString().replaceAll(",", " ").slice(2+sender[i].length)
            messages.eq(i).append(content + " : <b class='sender-id'>" + sender[i] + "</b>")

        } else
            messages.eq(i).addClass("text-left")
    }
    /**
     if ($ === $("#user-id").text())
     $("#message").addClass("text-right")
     else
     $("#message").addClass("text-left")
     **/
}

$(function () {

    $(" #sendForm ").submit(function (e) {
        e.preventDefault();
        console.log("Preventing")
    });


    connect()
    organize()
    //$( "#connect" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
    $("#send").click(function () {
        sendName();
    });
});