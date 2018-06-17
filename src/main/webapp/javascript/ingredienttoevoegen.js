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

ingredienttoevoegen();