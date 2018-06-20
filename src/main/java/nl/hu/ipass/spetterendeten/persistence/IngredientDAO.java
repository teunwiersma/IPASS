package nl.hu.ipass.spetterendeten.persistence;

import java.util.List;

import nl.hu.ipass.spetterendeten.model.Ingredient;

public interface IngredientDAO {

	public List<Ingredient> findAll(String gebruikerid);
	public boolean save(Ingredient ingredient);
	public Ingredient findByID(int ingredientid);
}
