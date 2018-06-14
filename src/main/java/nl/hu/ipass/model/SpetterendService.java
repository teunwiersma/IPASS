package nl.hu.ipass.model;

import java.util.List;

import nl.hu.ipass.persistence.GerechtDAO;
import nl.hu.ipass.persistence.GerechtPostgresDAOImpl;
import nl.hu.ipass.persistence.IngredientDAO;
import nl.hu.ipass.persistence.IngredientPostgresDAOImpl;

public class SpetterendService {

	private GerechtDAO gerechtdaoimpl = new GerechtPostgresDAOImpl();
	private IngredientDAO ingredientdaoimpl = new IngredientPostgresDAOImpl();
	
	public List<Gerecht> getAllGerechten(){
		return gerechtdaoimpl.findAll();
	}
	
	public List<Ingredient> getAllIngredienten(){
		return ingredientdaoimpl.findAll();
	}
	
	public Ingredient getIngredientByID(int ingredientID) {
		return ingredientdaoimpl.findByID(ingredientID);
	}
	
	public Gerecht getGerechtByNaam(String naam) {
		return gerechtdaoimpl.findByNaam(naam);
	}
	
	public Gerecht saveGerecht( int gerechtid, String naam, int ingredientid, int gebruikerid, String ingredient1,
			String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6,
			String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11,
			String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16) {
		for (Gerecht gerecht : getAllGerechten()) {
			if(!(gerecht.getNaam() == (naam))) {
				
				Gerecht newgerecht = new Gerecht ( gerechtid,  naam,  ingredientid,  gebruikerid,  ingredient1,
						 ingredient2,  ingredient3,  ingredient4,  ingredient5,  ingredient6,
						 ingredient7,  ingredient8,  ingredient9,  ingredient10,  ingredient11,
						 ingredient12,  ingredient13,  ingredient14,  ingredient15,  ingredient16 );
				gerechtdaoimpl.save(newgerecht);
				return newgerecht;
			}
		}
		return null;
	}
	
	
	public Ingredient saveingredient( String naam, int energie, int water, int eiwit, int koolhydraten, int suikers, int vet, int gebruikerid) {
		for (Ingredient ingredient : getAllIngredienten()) {
			
				
				Ingredient newingredient = new Ingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
				ingredientdaoimpl.save(newingredient);
				return newingredient;
			
		}
		return null;
	}
	
}
