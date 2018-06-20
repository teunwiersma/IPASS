
function login(event){
    document.querySelector("#login").addEventListener("click", function(){

     var username = document.querySelector("#username").value;
	 var password = document.querySelector("#password").value;
	 window.sessionStorage.setItem('username', username);
	 window.sessionStorage.setItem('password', password);

	 var formData = new FormData(document.querySelector("#loginform"));
	 var encData = new URLSearchParams(formData.entries());
	 

	 fetch("restservices/authentication/", { method : 'POST', body: encData})
	        .then(function(response){
	         if(response.ok){
	            //window.location.assign("gerechttoevoegen.html");
	            return response.json();
	          }else{
	                alert("Wrong username/password");
	                throw "Wrong username/password";
	    }})
	    		.then(function(myToken){
	    			token = myToken.JWT;
	    			var base64Url = token.split('.')[1];
	    			var base64 = base64Url.replace('-', '+').replace('_', '/');
	    			var tokenS = JSON.parse(window.atob(base64));
	    			window.localStorage.setItem("gebruikerid", tokenS);
	    			console.log(tokenS);
	    			
	    		})
	            .then(myToken => window.sessionStorage.setItem("sessionToken", myToken.JWT))
	            .catch(error => console.log(error));
	    });
}

//function logGebruikerid(){
//    document.querySelector("#login").addEventListener("click", function(){
//        var username = document.querySelector("#username").value;
//    	if(username == "teun"){
//    		window.localStorage.setItem('gebruikerid', "1");
//    	}
//    	else{
//    		window.localStorage.setItem('gebruikerid', "2");
//    	}
//    		
//    	
//    })
//}

//logGebruikerid();
login();