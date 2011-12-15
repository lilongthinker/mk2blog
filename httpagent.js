var http = require('http');
var count = 10;
var agent = new http.Agent({"maxSockets" : 3});
var options = {
        host: 'localhost',
        port: 3458,
        path: '/',
        method: 'GET',
        agent : agent,
        headers : {"Connection" : "keep-alive"}
};
function looptest(){
    var req = http.request(options, function(res){
        res.on("end", function(){
            console.log("count : " + count);
            if(--count <= 0){ 

            }else{
                process.nextTick(function(){
                    looptest();
                }); 
            }   
        }); 
    }); 
    req.end();
}

looptest();