package nl.hu.ipass.spetterendeten.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.ipass.spetterendeten.model.Ingredient;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;
import nl.hu.ipass.spetterendeten.persistence.UserDao;
import nl.hu.ipass.spetterendeten.persistence.UserPostgresDaoImpl;

@Path("/ingredientGerecht")
public class ingredientGerechtResource {
	
	public static String gebruikerid;
	
	private SpetterendService service = ServiceProvider.getSpetterendService();

	@POST
	@Produces("application/json")
	public String getGebruikerid(@FormParam("username")String username,
								  @FormParam("password")String password){
		
		
		UserDao dao = new UserPostgresDaoImpl();
		ingredientGerechtResource.gebruikerid = dao.findGebruikerIDForUser(username, password);
		
		return gebruikerid;
		
	}
	
	@GET
	@Produces("application/json")
	public String getIngredienten() {
		
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Ingredient i : service.getAllIngredienten(gebruikerid)) {
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
