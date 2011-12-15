
var http = require('http');

http.createServer(function(req, res) {
    res.end(req.socket.remotePort + '');
}).listen(3458);