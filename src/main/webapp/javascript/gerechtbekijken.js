function initpage(){
	document.querySelector("#username").setAttribute("value", window.sessionStorage.getItem('username'));
	document.querySelector("#password").setAttribute("value", window.sessionStorage.getItem('password'));

	gebruikeridFunctie();
	
	knopjes();
	laadOpenbaarGerecht();
	zoekGerecht();
	
	

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

function refreshTabel(){
	document.querySelector("#gerechtennaamlijst").innerHTML = " ";
	
	var row = document.createElement("tr");
	
	var naamrow = document.createElement("th");
	naamrow.appendChild(document.createTextNode("Gerecht:"));
	
	row.appendChild(naamrow);
	
	document.querySelector("#gerechtennaamlijst").appendChild(row);
}

function zoekGerecht(){
	document.querySelector("#zoekgerecht").addEventListener("click", function(){
		refreshTabel();
		var naamgerecht = document.querySelector("#invoergerechtnaam").value;
		console.log(naamgerecht);
		fetch("restservices/gerechten/zoekgerecht/" + naamgerecht , {method:'GET'})
		.then(response => response.json())
		.then(function(namen){
			for(const naam of namen){
				var row = document.createElement("tr");

				var naamgerechtColumn = document.createElement("td");
				var naamgerechtText = document.createTextNode(naam.naamgerecht);
				naamgerechtColumn.appendChild(naamgerechtText);
				row.appendChild(naamgerechtColumn);
				
				document.querySelector("#gerechtennaamlijst").appendChild(row);
				}
		})
		
		
	})
		
	
	
}



initpage();