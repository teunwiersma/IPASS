package nl.hu.ipass.spetterendeten.webservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.google.gson.*;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;
import nl.hu.ipass.spetterendeten.model.ingredientGerecht;
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
		
		Gerecht newgerecht = service.saveGerecht(gebruikerid, naam);
		System.out.println(newgerecht);
		return Response.ok(newgerecht).build();
	}
	
	@Path("/ingredienttoevoegen")
	@POST
	@Produces("application/json")
	public Response addIngredienten(@FormParam("ArrayList")String ingredienten){
		System.out.println(ingredienten);
		List<String> array = new ArrayList<String>(Arrays.asList(ingredienten.split(",")));
		System.out.println(array);
//		JSONArray array = new JSONArray(ingredienten);
//		for (int i = 0; i < array.length(); i++) {
//			 JSONObject object = array.getJSONObject(i);
//			 ingredientGerecht ingredientgerecht = new ingredientGerecht(object.getInt("ingredientid"), object.getInt("gerechtid");
//		}
//		String Json = new Gson().toJson(ingredienten);
//		Json = Json.replace("\"", "").replace("\\", "");
//		System.out.println(Json);
//		
//		com.google.gson.JsonObject object = new com.google.gson.JsonObject(ingredienten);
//	
//		for (int i = 0; i < Json.length(); i++) {
			
		   //ingredientGerecht ingredientGerecht = service.saveIngredientenGerecht(ingredientid, gerechtid);
	//	}
		 
			
		return Response.ok().build();
	}

	
	@Path("/zoekgerecht/{naam}")
	@GET
	@Produces("application/json")
	public String zoekGerecht(@PathParam("naam")String naam) {
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Gerecht g: service.getNaamGerecht(naam)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naamgerecht", g.getNaamGerecht());
			job.add("gerechtid" , g.getGerechtid());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("/zoekingredient/{gerechtid}")
	@GET
	@Produces("application/json")
	public String zoekIngredient(@PathParam("gerechtid")int gerechtid) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Gerecht g: service.getAllIngredientenvGerecht(gerechtid)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naamingredient", g.getNaamingredient());
			job.add("eiwit", g.getEiwit());
			job.add("energie", g.getEnergie());
			job.add("water", g.getWater());
			job.add("koolhydraten", g.getKoolhydraten());
			job.add("suikers", g.getSuikers());
			job.add("vet", g.getVet());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
	
	@GET
	@Produces("application/json")
	public String getIngredientenGerecht() {
		 
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Gerecht g : service.getAllNaamGerecht(gebruikerid)) {
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