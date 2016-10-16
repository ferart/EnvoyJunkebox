var express = require("express");
var path = require("path");
var bodyParser = require("body-parser");


var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

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
	
	if (!(req.body.token && req.body.message)) {
		handleError(res, "Invalid parameters", "Must provide token and message ", 400);
	}else{
		//console.log(req.body);
	   res.status(200).json({"success": req.body.message});
	}
  
});