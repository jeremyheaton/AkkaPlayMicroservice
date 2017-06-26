# AkkaPlayMicroservice

The service can be communicated using a web api, all requests must be content-type:application/json


endpoints    
POST &nbsp;&nbsp;&nbsp;   / &nbsp;&nbsp;&nbsp;&nbsp;creates a new supscription with related messages  
POST &nbsp;&nbsp;&nbsp;   /messages    &nbsp;&nbsp;&nbsp;               accepts a message and updates all supscriptions  
POST &nbsp;&nbsp;&nbsp;   /editSupscriptionMessage &nbsp;&nbsp;&nbsp;   accepts a SupscriptionMessage with a method type to decide how to edit the supscription  
GET  &nbsp;&nbsp;&nbsp;   /:id      &nbsp;&nbsp;&nbsp;                  retreive information for a supscription


A subscription takes an array of messageTypes. 
```
fetch('http://localhost:9000', {
    method: 'post',
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    },
	body: JSON.stringify({
		subscription: [{messageType: 'message1'}, {messageType: 'message2'}, {messageType: 'message5'}]})
}).then(function(response) {
    return response.json().then(function(json) {
		console.log(json)
    });
  });
```  

A message is just a simple json key value. 
```
fetch('http://localhost:9000/messages', {
    method: 'post',
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    },
	body: JSON.stringify({
		messageType: 'message8'})
}).then(function(response) {
    return response.json().then(function(json) {
		console.log(json)
    });
  });
```

Retreiving a subscription
```
fetch('http://localhost:9000/1', {
    method: 'get',
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    }
}).then(function(response) {
    return response.json().then(function(json) {
		console.log(json)
    });
  });
```

Unsubscribing from a message. Same endpoint as subscribing too a message. Only difference is the method involved. 
```
fetch('http://localhost:9000/editSupscriptionMessage', {
    method: 'post',
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    },
	body: JSON.stringify({
		message : {messageType: 'message8'}, 
		id : 1, 
		method: "remove"}
	)
}).then(function(response) {
    return response.json().then(function(json) {
		console.log(json)
    });
  });
```

Subscribe to a message. Same api endpoint as unsubscribing from a message. Only difference is the method string. 
```
fetch('http://localhost:9000/editSupscriptionMessage', {
    method: 'post',
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    },
	body: JSON.stringify({
		message : {messageType: 'message8'}, 
		id : 1, 
		method: "add"}
	)
}).then(function(response) {
    return response.json().then(function(json) {
		console.log(json)
    });
  });
```
