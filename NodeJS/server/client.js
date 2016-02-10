var http = require('http');
var events = require('events');
var eventEmitter = new events.EventEmitter();

// Options to be used by request 
var options = {
   host: 'localhost',
   port: '8080',
   path: '/index.html?command=5'  
};

// Callback function is used to deal with response
var callback = function(response){
   // Continuously update stream with data
   var body = '';
   response.on('data', function(data) {
      body += data;
   });

   eventEmitter.emit('my_connection');
   
   response.on('end', function() {
      // Data received completely.
      console.log(body);
   });
}
// Make a request to the server
var req = http.request(options, callback);

req.end();
