package nl.hu.ipass.persistence;

import java.util.List;

import nl.hu.ipass.model.Ingredient;

public interface IngredientDAO {

	public List<Ingredient> findAll();
	public boolean save(Ingredient ingredient);
	public Ingredient findByID(int ingredientid);
}
