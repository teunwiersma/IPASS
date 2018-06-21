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

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;
import nl.hu.ipass.spetterendeten.persistence.UserDao;
import nl.hu.ipass.spetterendeten.persistence.UserPostgresDaoImpl;

@Path("/openbaargerecht")
public class openbaarGerechtResource{
	
	private SpetterendService service = ServiceProvider.getSpetterendService();
	
	public static String gebruikerid;
	

	@POST
	@Produces("application/json")
	public String getGebruikerid(@FormParam("username")String username,
								  @FormParam("password")String password){
		
		
		UserDao dao = new UserPostgresDaoImpl();
		openbaarGerechtResource.gebruikerid = dao.findGebruikerIDForUser(username, password);
		
		return gebruikerid;
		
	}
	
	@GET
	@Produces("application/json")
	public String getGerechten() {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Gerecht g : service.getAllGerechten(gebruikerid)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naam" , g.getNaam());
			job.add("gerechtid", g.getGerechtid());
			job.add("gebruikerid" , g.getGebruikerid());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("/gerechtdelen")
	@POST
	@Produces("application/json")
	public Response deelGerecht(@FormParam("gerechtid")int gerechtid,
							  @FormParam("gebruikerid")int gebruikerid) {
		openbaarGerecht newopenbaargerecht =  service.gerechtdelen(gerechtid, gebruikerid);
		if(newopenbaargerecht == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Het delen is niet gelukt");
			return Response.status(409).entity(messages).build();
		}
		return Response.ok(newopenbaargerecht).build();
	}	
}