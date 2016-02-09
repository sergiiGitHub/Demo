var http = require('http');
var server = new http.Server();

server.listen(3000, '192.168.0.102');

var emit = server.emit;

server.emit = function(event){
  console.log(event);
  emit.apply( server,  arguments );
};

var count = 0;
server.on('request', function( req, res ){
    res.end( "Hello World!" + (++count) );
} );





