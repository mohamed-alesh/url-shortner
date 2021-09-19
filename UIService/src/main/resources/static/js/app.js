function OnSubmitForm() {
	document.myform.action = "/shorten/" + document.getElementById("url").value;
	connect();
	return true;
}


var stompClient = null;

function connect() {
	var socket = new SockJS('/shorts');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/pushmessages', function(messageOutput) {
			showMessageOutput(JSON.parse(messageOutput.body));
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function showMessageOutput(messageOutput) {
	var response = document.getElementById('shortURL');
	response.value = messageOutput.hash;
	document.getElementById('msg').value = "Congratulations, your short URL is here!";
}

