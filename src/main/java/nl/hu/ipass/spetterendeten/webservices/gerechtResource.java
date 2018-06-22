package nl.hu.ipass.spetterendeten.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;
import nl.hu.ipass.spetterendeten.persistence.UserDao;
import nl.hu.ipass.spetterendeten.persistence.UserPostgresDaoImpl;

@Path("/gerechten")
public class gerechtResource{
	
	private SpetterendService service = ServiceProvider.getSpetterendService();
	
	public static String gebruikerid;

	@POST
	@Produces("application/json")
	public String getGebruikerid(@FormParam("username")String username,
								 @FormParam("password")String password){
		
		
		UserDao dao = new UserPostgresDaoImpl();
		gerechtResource.gebruikerid = dao.findGebruikerIDForUser(username, password);
		
		return gebruikerid;
		
	}
	
	@Path("/gerechttoevoegen")
	@POST
	@Produces("application/json")
	public Response addGerecht(@FormParam("naam")String naam,
							   @FormParam("gebruikerid")int gebruikerid) {
		
		Gerecht newgerecht = service.saveGerecht(naam, gebruikerid);
		System.out.println(newgerecht);
		return Response.ok(newgerecht).build();
	}
	
	@Path("/zoekgerecht/{naam}")
	@GET
	@Produces("application/json")
	public String zoekGerecht(@PathParam("naam")String naam) {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Gerecht g: service.getNaamGerecht(naam)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naamgerecht", g.getNaamGerecht());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	@GET
	@Produces("application/json")
	public String getIngredientenGerecht() {
		 
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Gerecht g : service.getAllIngredientenGerecht(gebruikerid)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("gebruikerid" , g.getGebruikerid());
			job.add("naamingredient", g.getNaamingredient());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("/naamgerecht")
	@GET
	@Produces("application/json")
	public String getNaamGerecht() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Gerecht g : service.getAllNaamGerecht(gebruikerid)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naamgerecht" , g.getNaamGerecht());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("/openbaargerecht")
	@GET
	@Produces("application/json")
	public String getOpenbaarGerecht() {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (openbaarGerecht g : service.getAllOpenbaarGerechten(gebruikerid)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naamgerecht" , g.getGerechtNaam());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
}