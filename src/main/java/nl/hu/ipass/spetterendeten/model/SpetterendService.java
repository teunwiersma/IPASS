package nl.hu.ipass.spetterendeten.model;

import java.util.List;

import nl.hu.ipass.spetterendeten.persistence.GerechtDAO;
import nl.hu.ipass.spetterendeten.persistence.GerechtPostgresDAOImpl;
import nl.hu.ipass.spetterendeten.persistence.IngredientDAO;
import nl.hu.ipass.spetterendeten.persistence.IngredientPostgresDAOImpl;


public class SpetterendService {

	private GerechtDAO gerechtdaoimpl = new GerechtPostgresDAOImpl();
	private IngredientDAO ingredientdaoimpl = new IngredientPostgresDAOImpl();
	
	public List<Gerecht> getAllGerechten(String gebruikerid){
		return gerechtdaoimpl.findAll(gebruikerid);
	}
	
	public List<Ingredient> getAllIngredienten(String gebruikerid){
		return ingredientdaoimpl.findAll(gebruikerid);
	}
	
	public Ingredient getIngredientByID(int ingredientID) {
		return ingredientdaoimpl.findByID(ingredientID);
	}
	
	public Gerecht getGerechtByNaam(String naam) {
		return gerechtdaoimpl.findByNaam(naam);
	}
	
	public Gerecht saveGerecht( int gerechtid, String naam, String gebruikerid) {
		for (Gerecht gerecht : getAllGerechten(gebruikerid)) {
			if(!(gerecht.getNaam() == (naam))) {
				
				Gerecht newgerecht = new Gerecht ( gerechtid,  naam,  gebruikerid);
				gerechtdaoimpl.save(newgerecht);
				return newgerecht;
			}
		}
		return null;
	}
	
	
	public Ingredient saveingredient( String naam, int energie, int water, int eiwit, int koolhydraten, int suikers, int vet, int gebruikerid) {			
				
				Ingredient newingredient = new Ingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
				ingredientdaoimpl.save(newingredient);
				return newingredient;
			
		
	}
	
	
	
	
}
