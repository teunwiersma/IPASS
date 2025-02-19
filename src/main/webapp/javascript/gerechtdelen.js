function initpage(){
	document.querySelector("#username").setAttribute("value", window.sessionStorage.getItem('username'));
	document.querySelector("#password").setAttribute("value", window.sessionStorage.getItem('password'));

	laadgerechten()
	knopjes();
	gebruikeridFunctie();
	gerechtDelen();
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
	fetch("restservices/openbaargerecht/")
	.then(response => response.json())
	.then(function(gerechten){
		for(const gerecht of gerechten){
			var row = document.createElement("tr");
			
			row.setAttribute('id', gerecht.gerechtid);
			
			var naamColumn = document.createElement("td");
		    var naamText = document.createTextNode(gerecht.naamgerecht);
		    naamColumn.appendChild(naamText);
		    row.appendChild(naamColumn);
		    
		    var gerechtidColumn = document.createElement("td");
		    var gerechtidText = document.createTextNode(gerecht.gerechtid);
		    gerechtidColumn.appendChild(gerechtidText);
		    row.appendChild(gerechtidColumn);
		    
		    var gebruikeridColumn = document.createElement("td");
		    var gebruikeridText = document.createTextNode(gerecht.gebruikerid);
		    gebruikeridColumn.setAttribute = ('hidden');
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
				 document.querySelector("#naam").setAttribute("value", gerecht.naamgerecht)
		      	 document.querySelector("#gerechtid").setAttribute("value", gerecht.gerechtid);
		      	 document.querySelector("#gebruikerid").setAttribute("value", gerecht.gebruikerid);
					
		    })
		}
	})
}

function gerechtDelen(){
	document.querySelector("#delen").addEventListener("click", function(){
		var formData = new FormData(document.querySelector("#deelform"));
		var encData = new URLSearchParams(formData.entries());
		
		var fetchoptions = {method: 'POST', body:encData};
		
		fetch("restservices/openbaargerecht/gerechtdelen", fetchoptions)
		.then(response => response.json(), alert("Gerecht gedeeld!"))
		.then(function(myJson){console.log(myJson); });
		
	})
}

function gebruikeridFunctie(){

    var formData = new FormData(document.querySelector("#ontzichtbaar"));
    var encData = new URLSearchParams(formData.entries());


    var fetchoptions = {method: 'POST', body:encData};

    fetch("restservices/openbaargerecht", fetchoptions)
    .then(response => response.json())
    .then(function(myJson){ console.log(myJson); });
          
}

initpage();
