function initpage(){
	document.querySelector("#username").setAttribute("value", window.sessionStorage.getItem('username'));
	document.querySelector("#password").setAttribute("value", window.sessionStorage.getItem('password'));

	gebruikeridFunctie();
	
	knopjes();
	laadOpenbaarGerecht();
	laadNaamGerechten();
	
	var coll = document.getElementsByClassName("collapsible");
	var i;

	for (i = 0; i < coll.length; i++) {
	  coll[i].addEventListener("click", function() {
	    this.classList.toggle("active");
	    var content = this.nextElementSibling;
	    if (content.style.maxHeight){
	      content.style.maxHeight = null;
	    } else {
	      content.style.maxHeight = content.scrollHeight + "px";
	    } 
	  });
	}
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
	
	document.querySelector("#uitlogknopje").addEventListener("click", function(){
		window.location.assign("index.html");
	});
	 
    if(window.sessionStorage.getItem('username') == null){
        document.querySelector("#naamingelogd").innerHTML = "Ingelogd als";
    }else{
        document.querySelector("#naamingelogd").innerHTML = "Ingelogd als " + window.sessionStorage.getItem('username');
    }

}

function laadgerechten(){
	fetch("restservices/gerechten/")
	.then(response => response.json())
	.then(function(gerechten){
		{ console.log(gerechten); }
	
		
		for(const gerecht of gerechten){
			var row = document.createElement("tr");
			
		
		    
		    var naamingredientColumn = document.createElement("td");
		    var naamingredientText = document.createTextNode(gerecht.naamingredient);
		    naamingredientColumn.appendChild(naamingredientText);
		    row.appendChild(naamingredientColumn);
		    
		   
		    
		    document.querySelector("#gerechtenlijst").appendChild(row);
		}
	})
}

function laadNaamGerechten(){
	fetch("restservices/gerechten/naamgerecht")
	.then(response => response.json())
	.then(function(gerechten){
		{ console.log(gerechten); }
		for(const gerecht of gerechten){
//			var naamGerechtButton = document.createElement("input");
//			naamGerechtButton.setAttribute('value', gerecht.naamgerecht);
//			naamGerechtButton.setAttribute('type', 'button');
//			naamGerechtButton.setAttribute('class', 'collapsible');
//			
//			var ingredientendiv = document.createElement("div");
//			ingredientendiv.setAttribute('id', "gerechtenlijst");
//			document.querySelector("#ingredienten").appendChild(naamGerechtButton);
			var row = document.createElement("tr");
			
		
		    
		    var naamingredientColumn = document.createElement("td");
		    var naamingredientText = document.createTextNode(gerecht.naamgerecht);
		    naamingredientColumn.appendChild(naamingredientText);
		    row.appendChild(naamingredientColumn);
		    
		   
		    
		    document.querySelector("#gerechtenlijst").appendChild(row);
			
			
		}
	})
}

function laadOpenbaarGerecht(){
	fetch("restservices/gerechten/openbaargerecht")
	.then(response => response.json())
	.then(function(gerechten){
		for (const gerecht of gerechten){
			var row = document.createElement("tr");
			
			row.setAttribute('id', gerecht.gerechtid);
			
			var openbaarGerechtidColumn = document.createElement("td");
			var openbaarGerechtidText = document.createTextNode(gerecht.naamgerecht);
			openbaarGerechtidColumn.appendChild(openbaarGerechtidText);
			row.appendChild(openbaarGerechtidColumn);
			
		    
		    document.querySelector("#openbaregerechtenlijst").appendChild(row);
		}
	})
}

function gebruikeridFunctie(){

    var formData = new FormData(document.querySelector("#ontzichtbaar"));
    var encData = new URLSearchParams(formData.entries());


    var fetchoptions = {method: 'POST', body:encData};

    fetch("restservices/gerechten", fetchoptions)
    .then(response => response.json())
    .then(function(myJson){ console.log(myJson); });
          
}

initpage();