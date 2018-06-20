package nl.hu.ipass.spetterendeten.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.spetterendeten.model.Ingredient;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;

@Path("/ingredienten")
public class SpetterendetenResource {
	
	private SpetterendService service = ServiceProvider.getSpetterendService();

	@POST
	@Produces("application/json")
	public Response addIngredient(
							   @FormParam("naam")String naam,
							   @FormParam("gebruikerid") int gebruikerid,
							   @FormParam("energie") int energie,
							   @FormParam("water") int water,
							   @FormParam("eiwit") int eiwit,
							   @FormParam("koolhydraten")int koolhydraten,
							   @FormParam("suikers")int suikers,
		   					   @FormParam("vet")int vet){
		Ingredient newIngredient = service.saveingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
		System.out.println(newIngredient);
		if(newIngredient == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Het opslaan is niet gelukt");
			return Response.status(409).entity(messages).build();	
		}
		return Response.ok(newIngredient).build();
	}

	@GET
	@Produces("application/json")
	public String getIngredienten() {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Ingredient i : service.getAllIngredienten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("ingredientid", i.getIngredientid());
			job.add("Ingredient", i.getNaam());
			job.add("energie", i.getEnergie());
			job.add("water", i.getWater());
			job.add("eiwit", i.getEiwit());
			job.add("koolhydraten", i.getKoolhydraten());
			job.add("suikers", i.getSuikers());
			job.add("vet", i.getVet());
			job.add("gebruikersid", i.getGebruikerid());
			
			jab.add(job);

		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
}
