function ingredienttoevoegen(){

	  document.querySelector("#opslaaningredient").addEventListener("click", function (){
		  console.log("heknek")
	      var formData = new FormData(document.querySelector("#toevoegenform"));
	      var encData = new URLSearchParams(formData.entries());

	      console.log("bbbbb")
	      fetch("restservices/ingredienten/", {method: 'POST', body: encData})
	      .then(response => response.json())
	      .then(function(myJson){ console.log(myJson); });
		})
}

function knopjes(){

	document.querySelector("#gerechttoevoegen").addEventListener("click", function(){
		window.location.assign("gerechttoevoegen.html");
	});
	
	document.querySelector("#gerechtaanpassen").addEventListener("click", function(){
		window.location.assign("gerechtaanpassen.html");
	});
	
	document.querySelector("#gerechtbekijken").addEventListener("click", function(){
		window.location.assign("gerechtbekijken.html");
	});
	document.querySelector("#gerechtdelen").addEventListener("click", function(){
		window.location.assign("gerechtdelen.html");
	});
	document.querySelector("#ingredienttoevoegen").addEventListener("click", function(){
		window.location.assign("ingredienttoevoegen.html");
	});
	document.querySelector("#ingredientaanpassen").addEventListener("click", function(){
		window.location.assign("ingredientaanpassen.html");
	});
	document.querySelector("#boodschappenlijstaanmaken").addEventListener("click", function(){
		window.location.assign("boodschappenlijstaanmaken.html");
	});
	document.querySelector("#boodschappenlijstaanpassen").addEventListener("click", function(){
		window.location.assign("boodschappenlijstaanpassen.html");
	});

}

knopjes();
ingredienttoevoegen();