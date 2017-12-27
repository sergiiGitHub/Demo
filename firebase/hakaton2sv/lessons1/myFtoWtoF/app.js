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

//get from firebase
var message = document.getElementById('txtMessage');
var dbRefM = database.ref().child('message');
dbRefM.on('value', snap => message.value = snap.val());







//send to firebase
var btn = document.getElementById("btnSend")
btn.addEventListener("click", writeHandlerWrong); //change

// suggestion














//incorrect ref
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

function writeHandler() {
	console.log("writeHandler:");
	console.log(firebase);

	var dbRefM = database.ref().child('message');
	dbRefM.set(message.value);
	
	console.log("write dbRefM: " + dbRefM);
}


