function initpage(){

	laadingredienten();

	knopjes();
	
}

function toevoegenGerecht(){
	  document.querySelector("#opslaan").addEventListener("click", function (){

      var formData = new FormData(document.querySelector("#nieuwgerecht"));
      var encData = new URLSearchParams(formData.entries());

      var fetchoptions = {method: 'POST', body:encData};
      
      fetch("restservices/gerecht", fetchoptions)
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
	
	document.querySelector("#uitlogknopje").addEventListener("click", function(){
		window.location.assign("index.html");
	});
	 
    if(window.sessionStorage.getItem('username') == null){
        document.querySelector("#naamingelogd").innerHTML = "Ingelogd als";
    }else{
        document.querySelector("#naamingelogd").innerHTML = "Ingelogd als " + window.sessionStorage.getItem('username');
    }

}

function laadingredienten(){
	fetch("restservices/ingredienten/")
	.then(response => response.json())
	.then(function(ingredienten){
		
		for(const ingredient of ingredienten){
			var row = document.createElement("tr");
			row.className = "naam";
			
			row.setAttribute("id", ingredient.ingredientid);
			
			var naamColumn = document.createElement("td");
		    var naamText = document.createTextNode(ingredient.Ingredient);
		    naamColumn.appendChild(naamText);
		    row.appendChild(naamColumn);
		    
			var energieColumn = document.createElement("td");
		    var energieText = document.createTextNode(ingredient.energie);
		    energieColumn.appendChild(energieText);
		    row.appendChild(energieColumn);	
		    
			var waterColumn = document.createElement("td");
		    var waterText = document.createTextNode(ingredient.water);
		    waterColumn.appendChild(waterText);
		    row.appendChild(waterColumn);
		    
			var eiwitColumn = document.createElement("td");
		    var eiwitText = document.createTextNode(ingredient.eiwit);
		    eiwitColumn.appendChild(eiwitText);
		    row.appendChild(eiwitColumn);
		    
			var koolhydratenColumn = document.createElement("td");
		    var koolhydratenText = document.createTextNode(ingredient.koolhydraten);
		    koolhydratenColumn.appendChild(koolhydratenText);
		    row.appendChild(koolhydratenColumn);
		    
			var suikersColumn = document.createElement("td");
		    var suikersText = document.createTextNode(ingredient.suikers);
		    suikersColumn.appendChild(suikersText);
		    row.appendChild(suikersColumn);
		    
			var vetColumn = document.createElement("td");
		    var vetText = document.createTextNode(ingredient.vet);
		    vetColumn.appendChild(vetText);
		    row.appendChild(vetColumn);
		    
		    var toevoegcolumn = document.createElement("td");
		    var toevoeg = document.createElement("button");
		    toevoeg.innerHTML = '+';
		    toevoeg.setAttribute =('id', ingredient.ingredientid);
		    toevoegcolumn.appendChild(toevoeg);
		    row.appendChild(toevoegcolumn);
		   
		    
			document.querySelector("#ingredientenlijst").appendChild(row);
		    
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


initpage();
