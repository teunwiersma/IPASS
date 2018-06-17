package nl.hu.ipass.spetterendeten.model;

public class Ingredient {

	private int ingredientid;

	private String naam;
	private int energie;
	private int water;
	private int eiwit;
	private int koolhydraten;
	private int suikers;
	private int vet;
	private int gebruikerid;

	public Ingredient(String naam, int energie, int water, int eiwit, int koolhydraten, int suikers, int vet,
			int gebruikerid) {
		this.naam = naam;
		this.energie = energie;
		this.water = water;
		this.eiwit = eiwit;
		this.koolhydraten = koolhydraten;
		this.suikers = suikers;
		this.vet = vet;
		this.gebruikerid = gebruikerid;
	}

	public int getIngredientid() {
		return ingredientid;
	}

	public void setIngredientid(int ingredientid) {
		this.ingredientid = ingredientid;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getEiwit() {
		return eiwit;
	}

	public void setEiwit(int eiwit) {
		this.eiwit = eiwit;
	}

	public int getKoolhydraten() {
		return koolhydraten;
	}

	public void setKoolhydraten(int koolhydraten) {
		this.koolhydraten = koolhydraten;
	}

	public int getSuikers() {
		return suikers;
	}

	public void setSuikers(int suikers) {
		this.suikers = suikers;
	}

	public int getVet() {
		return vet;
	}

	public void setVet(int vet) {
		this.vet = vet;
	}

	public int getGebruikerid() {
		return gebruikerid;
	}

	public void setGebruikderid(int gebruikerid) {
		this.gebruikerid = gebruikerid;
	}
}
