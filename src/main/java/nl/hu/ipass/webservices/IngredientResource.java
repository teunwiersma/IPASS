package nl.hu.ipass.webservices;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.ipass.model.Ingredient;
import nl.hu.ipass.model.ServiceProvider;
import nl.hu.ipass.model.SpetterendService;

@Path("/ingredient")
public class IngredientResource {

	private SpetterendService service = ServiceProvider.getSpetterendService();

	@POST
	@Produces("application/json")
	public Response addIngredient(
							   @FormParam("naam")String naam,
							   @FormParam("gerechtid")int gerechtid,
							   @FormParam("gebruikerid") int gebruikerid,
							   @FormParam("energie") int energie,
							   @FormParam("water") int water,
							   @FormParam("eiwit") int eiwit,
							   @FormParam("koolhydraten")int koolhydraten,
							   @FormParam("suikers")int suikers,
		   					   @FormParam("vet")int vet){
		
		Ingredient newIngredient = service.saveingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
		System.out.println(newIngredient);
		return Response.ok(newIngredient).build();
	}
}
