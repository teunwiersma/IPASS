function initpage(){
	ingredienttoevoegen();
}

function toevoegenGerecht(){
	  document.querySelector("#opslaan").addEventListener("click", function (){

      var formData = new FormData(document.querySelector("#toevoegform"));
      var encData = new URLSearchParams(formData.entries());

      var fetchoptions = {method: 'POST', body:encData};
      
      fetch("/spetterendeten", fetchoptions)
      .then(response => response.json())
      .then(function(myJson){ console.log(myJson); });
            
	})
}

function ingredienttoevoegen(){

	  document.querySelector("#opslaaningredient").addEventListener("click", function (){
		  console.log("heknek")
	      var formData = new FormData(document.querySelector("#toevoegenform"));
	      var encData = new URLSearchParams(formData.entries());

	      var fetchoptions = {method: 'POST', body:encData};
	      
	      fetch("/restservices/ingredient", fetchoptions)
	      .then(response => response.json())
	      .then(function(myJson){ console.log(myJson); });
	            
		})
}


/*function login(event){
	document.querySelector("#login").addEventListener("click", function(){
		
		var username = document.querySelector("#username").value;
		var password = document.querySelector("#password").value;
		
		window.sessionStorage.setItem('username', username);
		window.sessionStorage.setItem('password', password);

		var formData = new FormData(document.querySelector("#loginform"));
		var encData = new URLSearchParams(formData.entries());
		
		fetch("restservices/authentication", { method : 'POST', body: encData})
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
            .catch(error => console.log(error));
    });
}

login();*/

initpage();