package nl.hu.ipass.webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.model.Gerecht;
import nl.hu.ipass.model.ServiceProvider;
import nl.hu.ipass.model.SpetterendService;

@Path("/spetterendeten")
public class GerechtResource {
	private SpetterendService service = ServiceProvider.getSpetterendService();

	@POST
	@Produces("application/json")
	public Response addGerecht(@FormParam("naam") String naam,
							   @FormParam("ingredientid") int ingredientid,
							   @FormParam("gerechtid") int gerechtid,
							   @FormParam("gebruikerid") int gebruikerid,
							   @FormParam("ingredient1") String i1,
							   @FormParam("ingredient2") String i2,
							   @FormParam("ingredient3") String i3,
							   @FormParam("ingredient4") String i4,
							   @FormParam("ingredient5") String i5,
							   @FormParam("ingredient6") String i6,
							   @FormParam("ingredient7") String i7,
							   @FormParam("ingredient8") String i8,
							   @FormParam("ingredient9") String i9,
							   @FormParam("ingredient10") String i10,
							   @FormParam("ingredient11") String i11,
							   @FormParam("ingredient12") String i12,
							   @FormParam("ingredient13") String i13,
							   @FormParam("ingredient14") String i14,
							   @FormParam("ingredient15") String i15,
							   @FormParam("ingredient16") String i16) {
		Gerecht newgerecht = service.saveGerecht(gerechtid, naam, ingredientid, gebruikerid, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16 );
				System.out.println(newgerecht);
				return Response.ok(newgerecht).build();
	}

}
