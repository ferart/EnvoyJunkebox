var fcm =require("node-gcm")
var express = require("express");
var path = require("path");
var bodyParser = require("body-parser");


var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

//API key
var sender= new fcm.Sender("AIzaSyDLr87CvfysBp8bk8s10UFAZyJIErIZwxM");
var server_port = process.env.OPENSHIFT_NODEJS_PORT || 8080
var server_ip_address = process.env.OPENSHIFT_NODEJS_IP ||  '0.0.0.0'
 // Initialize the app.
  var server = app.listen(server_port, server_ip_address, function () {
    var port = server.address().port;
    console.log("App now running on port", port);
  });
  
  // Generic error handler used by all endpoints.
function handleError(res, reason, message, code) {
  console.log("ERROR: " + reason);
  res.status(code || 500).json({"error": message});
}

/*
Route path: /users/:userId/books/:bookId
Request URL: http://localhost:3000/users/34/books/8989
req.params: { "userId": "34", "bookId": "8989" }
*/
app.post("/suggestion", function(req, res) {
	console.log("Got response: " + res.statusCode);
	
	if ((req.body.token && req.body.message)) {
		var message = new fcm.Message();
		console.log("token:"+req.body.token+" \n message: "+ req.body.message )
		var message = new fcm.Message({
			data: { message: req.body.message }
		});
		var regTokens = [req.body.token];
		sender.send(message, regTokens, function (err, response) {
			if(err) {
			  console.error(err);
			} else {
			  console.log(response);
			  //console.log(req.body);
			  res.status(200).json({"success": "message delivered"});
			}
		});
		
	}else{
		handleError(res, "Invalid parameters", "Must provide token and message ", 400);
	}
  
});