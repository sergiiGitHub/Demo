var http = require('http');
var url = require('url');

var server = new http.Server( function(req, res){
    //GET /echo?m=Hello
    console.log(req.method, req.url);

    // prse url & check string
    var urlParser = url.parse( req.url, true );
    // .....
    // query: 'm=Hello',
    // pathname: '/echo'
    // .....
    console.log( urlParser );

    if ( urlParser.pathname == '/echo' && urlParser.query.message ){
        res.end( urlParser.query.message );
    }else {
        res.statusCode = 404;
        res.end( "Page not Found" );
    }


});

server.listen(3000, '192.168.0.102');
