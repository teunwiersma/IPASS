package nl.hu.ipass.spetterendeten.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;

@Path("/gerechten")
public class gerechtResource{
	
	private SpetterendService service = ServiceProvider.getSpetterendService();
	
	
	@GET
	@Produces("application/json")
	public String getGerechten() {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Gerecht g : service.getAllGerechten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naam" , g.getNaam());
			job.add("gerechtid", g.getGerechtid());
			job.add("gebruikerid" , g.getGebruikerid());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}