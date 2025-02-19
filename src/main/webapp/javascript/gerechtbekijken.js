function initpage(){
	document.querySelector("#username").setAttribute("value", window.sessionStorage.getItem('username'));
	document.querySelector("#password").setAttribute("value", window.sessionStorage.getItem('password'));

	gebruikeridFunctie();
	
	knopjes();
	laadOpenbaarGerecht();
	zoekGerecht();
	zoekingredient();
	 laadgerechten();

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
	fetch("restservices/gerechten/naamgerecht")
	.then(response => response.json())
	.then(function(gerechten){
		{ console.log(gerechten); }
	
		
		for(const gerecht of gerechten){
			var row = document.createElement("tr");
			
		
		    
		    var naamingredientColumn = document.createElement("td");
		    var naamingredientText = document.createTextNode(gerecht.naamgerecht);
		    naamingredientColumn.appendChild(naamingredientText);
		    row.appendChild(naamingredientColumn);
		    
		    var toevoegcolumn = document.createElement("td");
		    var toevoeg = document.createElement("button");
		    toevoeg.innerHTML = '+';
		    toevoegcolumn.appendChild(toevoeg);
		    row.appendChild(toevoegcolumn);
		    
		    toevoeg.addEventListener("click", function(){
				event.preventDefault();
				document.querySelector("#invoergerechtnaam").setAttribute("value", gerecht.naamgerecht);
		    })
		    document.querySelector("#eigengerechten").appendChild(row);
		    
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
			
			 var toevoegcolumn = document.createElement("td");
			    var toevoeg = document.createElement("button");
			    toevoeg.innerHTML = '+';
			    toevoegcolumn.appendChild(toevoeg);
			    row.appendChild(toevoegcolumn);
			    
			    toevoeg.addEventListener("click", function(){
					event.preventDefault();
					document.querySelector("#invoergerechtnaam").setAttribute("value", gerecht.naamgerecht);
			    })
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

function refreshTabelgerecht(){
	document.querySelector("#gerechtennaamlijst").innerHTML = " ";
	
	var row = document.createElement("tr");
	
	var naamrow = document.createElement("th");
	naamrow.appendChild(document.createTextNode("Gerecht:"));
	
	row.appendChild(naamrow);
	
	document.querySelector("#gerechtennaamlijst").appendChild(row);
}

function zoekGerecht(){
	document.querySelector("#zoekgerecht").addEventListener("click", function(){
		refreshTabelgerecht();
		var naamgerecht = document.querySelector("#invoergerechtnaam").value;
		
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
				
				sessionStorage.setItem('gerechtid', naam.gerechtid);
				}
		})
	})
}

function zoekingredient(){
	document.querySelector("#zoekgerecht").addEventListener("click", function(){
		var gerechtid = window.sessionStorage.getItem('gerechtid');
		refreshTabelingredient();
		fetch("restservices/gerechten/zoekingredient/" + gerechtid, {methdo:'GET'})
		.then(response => response.json())
		.then(function(ingredienten){
			for(const ingredient of ingredienten){
				
				var row = document.createElement("tr");
				
				var ingredientColumn = document.createElement("td");
				var ingredientText = document.createTextNode(ingredient.naamingredient);
				ingredientColumn.appendChild(ingredientText);
				row.appendChild(ingredientColumn);
				
				var eiwitColumn = document.createElement("td");
				var eiwitText = document.createTextNode(ingredient.eiwit);
				eiwitColumn.appendChild(eiwitText);
				row.appendChild(eiwitColumn);
				
				var energieColumn = document.createElement("td");
				var energieText = document.createTextNode(ingredient.energie);
				energieColumn.appendChild(energieText);
				row.appendChild(energieColumn)
				
				var waterColumn = document.createElement("td");
				var waterText = document.createTextNode(ingredient.water);
				waterColumn.appendChild(waterText);
				row.appendChild(waterColumn)
				
				var koolhydratenColumn = document.createElement("td");
				var koolhydratenText = document.createTextNode(ingredient.koolhydraten);
				koolhydratenColumn.appendChild(koolhydratenText);
				row.appendChild(koolhydratenColumn)
				
				var suikersColumn = document.createElement("td");
				var suikersText = document.createTextNode(ingredient.eiwit);
				suikersColumn.appendChild(suikersText);
				row.appendChild(suikersColumn)
				
				var vetColumn = document.createElement("td");
				var vetText = document.createTextNode(ingredient.vet);
				vetColumn.appendChild(vetText);
				row.appendChild(vetColumn)
				
				
				document.querySelector("#ingredientennaamlijst").appendChild(row);
				
				
			}
		})
	});	
}

function refreshTabelingredient(){
	document.querySelector("#ingredientennaamlijst").innerHTML = " ";
	
	var row = document.createElement("tr");
	
	var naamrow = document.createElement("th");
	naamrow.appendChild(document.createTextNode("Ingredienten:"));
	row.appendChild(naamrow);
	
	var eiwitrow = document.createElement("th");
	eiwitrow.appendChild(document.createTextNode("eiwit"));
	row.appendChild(eiwitrow);
	
	var energierow = document.createElement("th");
	energierow.appendChild(document.createTextNode("energie"));
	row.appendChild(energierow);
	
	var waterrow = document.createElement("th");
	waterrow.appendChild(document.createTextNode("water"));
	row.appendChild(waterrow);
	
	var koolhydratenrow = document.createElement("th");
	koolhydratenrow.appendChild(document.createTextNode("koolhydraten"));
	row.appendChild(koolhydratenrow);
	
	var suikersrow = document.createElement("th");
	suikersrow.appendChild(document.createTextNode("suikers"));
	row.appendChild(suikersrow);
	
	var vetrow = document.createElement("th");
	vetrow.appendChild(document.createTextNode("vet"));
	row.appendChild(vetrow);
	
	document.querySelector("#ingredientennaamlijst").appendChild(row);
}



initpage();