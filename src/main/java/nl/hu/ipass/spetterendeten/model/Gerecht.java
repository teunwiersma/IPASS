package nl.hu.ipass.spetterendeten.model;

public class Gerecht {


	private int gerechtid;
	private String naamgerecht;
	private int gebruikerid;
	private String naamingredient;
	private int eiwit;
	private int energie;
	private int water;
	private int koolhydraten;
	private int suikers;
	private int vet;
	
	public Gerecht(int gerechtid, String naamgerecht, int gebruikerid ) {
		this.gerechtid = gerechtid;
		this.naamgerecht = naamgerecht;
		this.gebruikerid = gebruikerid;
		
	}
	

	public Gerecht( String naamgerecht, int gerechtid) {
		this.gerechtid = gerechtid;
		this.naamgerecht = naamgerecht;
	}

	public Gerecht( int gebruikerid, String naamgerecht) {
		this.gebruikerid = gebruikerid;
		this.naamgerecht = naamgerecht;
	}

	public Gerecht(String naamgerecht) {
		this.naamgerecht = naamgerecht;
	}

	public Gerecht(String naamingredient, int gerechtid, String naamgerecht) {
		this.naamingredient = naamingredient;
		this.gerechtid = gerechtid;
	}




	public Gerecht( String naamgerecht,int gerechtid,  int eiwit, int energie, int water,	int koolhydraten, int suikers, int vet ,String naamingredient) {
		this.gerechtid = gerechtid;
		this.naamgerecht = naamgerecht;
		this.naamingredient = naamingredient;
		this.eiwit = eiwit;
		this.energie = energie;
		this.water = water;
		this.koolhydraten = koolhydraten;
		this.suikers = suikers;
		this.vet = vet;
	}


	public int getGerechtid() {
		return gerechtid;
	}


	public void setGerechtid(int gerechtid) {
		this.gerechtid = gerechtid;
	}


	public String getNaamGerecht() {
		return naamgerecht;
	}


	public void setNaamGerecht(String naamgerecht) {
		this.naamgerecht = naamgerecht;
	}

	public int getGebruikerid() {
		return gebruikerid;
	}


	public void setGebruikerid(int gebruikerid) {
		this.gebruikerid = gebruikerid;
	}


	public String getNaamingredient() {
		return naamingredient;
	}


	public void setNaamingredient(String naamingredient) {
		this.naamingredient = naamingredient;
	}


	public String getNaamgerecht() {
		return naamgerecht;
	}


	public void setNaamgerecht(String naamgerecht) {
		this.naamgerecht = naamgerecht;
	}


	public int getEiwit() {
		return eiwit;
	}


	public void setEiwit(int eiwit) {
		this.eiwit = eiwit;
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
}