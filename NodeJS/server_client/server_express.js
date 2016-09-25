var express = require('express');
var app = express();

app.get('/', function (req, res) {
	//res.send('Hello World');
	res.sendFile( __dirname + "/" + "index.html" );
});

app.get('/process_get', function (req, res) {
   console.log("response :" + res);
   console.log("request :" + req.query.paramOne);
   response = {
       paramOne:"response"
   };
   console.log(response);
   res.end(JSON.stringify(response));});

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})
