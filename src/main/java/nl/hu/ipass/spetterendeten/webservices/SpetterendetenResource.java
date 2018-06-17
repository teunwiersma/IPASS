package nl.hu.ipass.spetterendeten.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
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
		System.out.println("aaaaa");
		Ingredient newIngredient = service.saveingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
		System.out.println(newIngredient);
		if(newIngredient == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Het opslaan is niet gelukt");
			return Response.status(409).entity(messages).build();	
		}
		return Response.ok(newIngredient).build();
	}

}
