// Initialize Firebase
var config = {
	apiKey: "AIzaSyBcOZ5jzAynIPlm_Z-QaAclCDO2uXJ9mWM",
	authDomain: "fir-sample-c6853.firebaseapp.com",
	databaseURL: "https://fir-sample-c6853.firebaseio.com",
	projectId: "fir-sample-c6853",
	storageBucket: "fir-sample-c6853.appspot.com",
	messagingSenderId: "1355804496"
};
firebase.initializeApp(config);
var database = firebase.database();
//from prev example
//var bigOne = document.getElementById('bigOne');
//var dbRef = database.ref().child('text');
//dbRef.on('value', snap => bigOne.innerText = snap.val());

var sMessage = document.getElementById('sendMessage');
var rMessage = document.getElementById('recieveMessage');

//get from firebase
var dbRefM = database.ref().child('message');
dbRefM.on('value', snap => rMessage.innerText = snap.val());

//send to firebase
var btn = document.getElementById("btnSend")
btn.addEventListener("click", writeHandler); //change

// suggestion
// incorrect ref
function writeHandlerWrong() {
	console.log("writeHandler:");
	console.log(firebase);

	var data = {
		name: "message",
		score: 43
	};

	database.ref().push(data);
	console.log("write data: " + data);
}

//correct
function writeHandler() {
	console.log("writeHandler:" + sMessage.value);
	console.log(firebase);

	var dbRefM = database.ref().child('message');
	var send = sMessage.value
	if (send) {
		dbRefM.set(sMessage.value);
	}
	
	console.log("write dbRefM: " + dbRefM);
}