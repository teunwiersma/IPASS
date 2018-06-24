package nl.hu.ipass.spetterendeten.model;

public class ingredientGerecht {

	private int ingredientid;
	private int gerechtid;
	
	public ingredientGerecht(int ingredientid, int gerechtid) {
		this.ingredientid = ingredientid;
		this.gerechtid = gerechtid;
	}

	public int getIngredientid() {
		return ingredientid;
	}

	public void setIngredientid(int ingredientid) {
		this.ingredientid = ingredientid;
	}

	public int getGerechtid() {
		return gerechtid;
	}

	public void setGerechtid(int gerechtid) {
		this.gerechtid = gerechtid;
	}

}
