var http = require('http');
var server = new http.Server( function(req, res){
    console.log(req.method, req.url);
});

server.listen(3000, '192.168.0.102');
