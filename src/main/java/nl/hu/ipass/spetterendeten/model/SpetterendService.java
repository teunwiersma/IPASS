package nl.hu.ipass.spetterendeten.model;

import java.util.List;

import nl.hu.ipass.spetterendeten.persistence.GerechtDAO;
import nl.hu.ipass.spetterendeten.persistence.GerechtPostgresDAOImpl;
import nl.hu.ipass.spetterendeten.persistence.IngredientDAO;
import nl.hu.ipass.spetterendeten.persistence.IngredientPostgresDAOImpl;


public class SpetterendService {

	private GerechtDAO gerechtdaoimpl = new GerechtPostgresDAOImpl();
	private IngredientDAO ingredientdaoimpl = new IngredientPostgresDAOImpl();
	
	public List<Gerecht> getAllIngredientenGerecht(String gebruikerid){
		return gerechtdaoimpl.findAllIngredientenGerecht(gebruikerid);
	}
	
	public List<openbaarGerecht> getAllOpenbaarGerechten(String gebruikerid){
		return gerechtdaoimpl.findAllOpenbaarGerecht(gebruikerid);
	}
	
	public List<Gerecht> getAllNaamGerecht(String gebruikerid){
		return gerechtdaoimpl.findAllNaamGerecht(gebruikerid);
	}
	
	public List<Gerecht> getNaamGerecht(String naamgerecht){
		return gerechtdaoimpl.findNaamGerecht(naamgerecht);
	}
	
	public List<Ingredient> getAllIngredienten(String gebruikerid){
		return ingredientdaoimpl.findAll(gebruikerid);
	}
	
	public Ingredient getIngredientByID(int ingredientID) {
		return ingredientdaoimpl.findByID(ingredientID);
	}
	
	
	public Gerecht saveGerecht(  String naam, int gebruikerid) {
				Gerecht newgerecht = new Gerecht (  naam,  gebruikerid);
				gerechtdaoimpl.save(newgerecht);
				return newgerecht;
	}
	
	
	
	public Ingredient saveingredient( String naam, int energie, int water, int eiwit, int koolhydraten, int suikers, int vet, int gebruikerid) {			
				
				Ingredient newingredient = new Ingredient( naam, energie, water, eiwit, koolhydraten, suikers, vet, gebruikerid);
				ingredientdaoimpl.save(newingredient);
				return newingredient;
			
		
	}
	
	public openbaarGerecht gerechtdelen(int gerechtid, int gebruikerid) {
		openbaarGerecht newopenbaargerecht = new openbaarGerecht(gerechtid, gebruikerid);
		gerechtdaoimpl.gerechtDelen(newopenbaargerecht);
		return newopenbaargerecht;
	}
	
	
	
	
}
