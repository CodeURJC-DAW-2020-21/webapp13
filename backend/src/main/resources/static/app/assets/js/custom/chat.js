var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            console.log("Mensaje recibido")
            showGreeting(JSON.parse(greeting.body).sender,JSON.parse(greeting.body).content);
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
    stompClient.send("/app/chat", {}, JSON.stringify({'content': $("#name").val(),'sender': $("#user-id").text()}));
}

function showGreeting(sender,message) {
    console.log("Sender: "+ sender + "Message: "+ message)
    $("#greetings").append("<tr><td>" + sender +": "+ message + "</td></tr>");
}

$(function () {

    $(" #sendForm ").submit(function (e) {
        e.preventDefault();
        console.log("Preventing")
    });


    connect()
    //$( "#connect" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});