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
		for(const gerecht of gerechten){
			var row = document.createElement("tr");
			
			row.setAttribute('id', gerecht.gerechtid);
			
			var naamColumn = document.createElement("td");
		    var naamText = document.createTextNode(gerecht.naam);
		    naamColumn.appendChild(naamText);
		    row.appendChild(naamColumn);
		    
		    var gerechtidColumn = document.createElement("td");
		    var gerechtidText = document.createTextNode(gerecht.gerechtid);
		    gerechtidColumn.appendChild(gerechtidText);
		    row.appendChild(gerechtidColumn);
		    
		    var gebruikeridColumn = document.createElement("td");
		    var gebruikeridText = document.createTextNode(gerecht.gebruikerid);
		    gebruikeridColumn.appendChild(gebruikeridText);
		    row.appendChild(gebruikeridColumn);
		    
		    
		    var toevoegcolumn = document.createElement("td");
		    var toevoeg = document.createElement("button");
		    toevoeg.innerHTML = '+';
		    toevoeg.setAttribute =('id', gerecht.gerechtid);
		    toevoegcolumn.appendChild(toevoeg);
		    row.appendChild(toevoegcolumn);
		    
		    document.querySelector("#gerechtenlijst").appendChild(row);
		    
		    toevoeg.addEventListener("click", function(){
				event.preventDefault();
					var rows = document.createElement("tr");
					
					var ingredientje = document.createElement("td");
					var ingredientjes = document.createTextNode(ingredient.Ingredient);
					ingredientje.appendChild(ingredientjes);
					rows.appendChild(ingredientje);
					
					var idcolumn = document.createElement("td");
					var idText = document.createTextNode(ingredient.ingredientid);
					idcolumn.appendChild(idText);
					rows.appendChild(idcolumn);
					
					document.querySelector("#gerechtlijst").appendChild(rows);
		    })
		}
	})
}
laadgerechten()
knopjes();