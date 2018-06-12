























function login(event){
	document.querySelector("#login").addEventListener("click", function(){
		
		var username = document.querySelector("#username").value;
		var password = document.querySelector("#password").value;
		
		window.sessionStorage.setItem('username', username);
		window.sessionStorage.setItem('password', password);

		var formData = new FormData(document.querySelector("#loginform"));
		var encData = new URLSearchParams(formData.entries());
		
		/*fetch("restservices/authentication", { method : 'POST', body: encData})
        .then(function(response){
         if(response.ok){
            location.reload();
            alert("U bent succesvol ingelogd");
            return response.json();
          }else{
                alert("Wrong username/password");
                throw "Wrong username/password";
    }})

            .then(myToken => window.sessionStorage.setItem("sessionToken", myToken.JWT))
            .catch(error => console.log(error));*/
    });
}

login();