package nl.hu.ipass.spetterendeten.model;

public class Gerecht {


	private int gerechtid;
	private String naamgerecht;
	private String gebruikerid;
	private String naamingredient;
	
	
	public Gerecht(int gerechtid, String naamgerecht, String gebruikerid ) {
		this.gerechtid = gerechtid;
		this.naamgerecht = naamgerecht;
		this.gebruikerid = gebruikerid;
		
	}
	

	public Gerecht(String naamgerecht) {
		this.naamgerecht = naamgerecht;
	}


	public Gerecht( String gebruikerid, String naamingredient) {
		this.gebruikerid = gebruikerid;
		this.naamingredient = naamingredient;
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

	public String getGebruikerid() {
		return gebruikerid;
	}


	public void setGebruikerid(String gebruikerid) {
		this.gebruikerid = gebruikerid;
	}


	public String getNaamingredient() {
		return naamingredient;
	}


	public void setNaamingredient(String naamingredient) {
		this.naamingredient = naamingredient;
	}
}