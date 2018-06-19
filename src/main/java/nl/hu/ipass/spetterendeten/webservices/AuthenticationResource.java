package nl.hu.ipass.spetterendeten.webservices;

import java.security.Key;
import java.util.Calendar;
import java.util.AbstractMap.SimpleEntry;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.ipass.spetterendeten.model.ServiceProvider;
import nl.hu.ipass.spetterendeten.model.SpetterendService;
import nl.hu.ipass.spetterendeten.model.User;
import nl.hu.ipass.spetterendeten.persistence.UserDao;
import nl.hu.ipass.spetterendeten.persistence.UserPostgresDaoImpl;

@Path("/authentication")
public class AuthenticationResource {

	private SpetterendService service = ServiceProvider.getSpetterendService();

	final static public Key key = MacProvider.generateKey();
	
	private String createToken(String username, String role) throws JwtException{
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expiration.getTime())
				.claim("role", role)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
		
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response auhenticationUser(@FormParam("username")String username,
									  @FormParam("password")String password) {
		try {
			UserDao dao = new UserPostgresDaoImpl();
			String role = dao.findGebruikerIDForUser(username, password);
			
			if(role == null) { throw new IllegalArgumentException("No user found");}
			
			String token = createToken(username, role);
			
			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			return Response.ok(JWT).build();
		
		}catch(JwtException | IllegalArgumentException e)
			{return Response.status(Response.Status.UNAUTHORIZED).build();}
	}
	
	
}
