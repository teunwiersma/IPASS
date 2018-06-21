package nl.hu.ipass.spetterendeten.model;

public class Gerecht {


	private int gerechtid;
	private String naam;
	private String gebruikerid;
	
	
	
	public Gerecht(int gerechtid, String naam, String gebruikerid) {
		this.gerechtid = gerechtid;
		this.naam = naam;
		this.gebruikerid = gebruikerid;
	}


	public int getGerechtid() {
		return gerechtid;
	}


	public void setGerechtid(int gerechtid) {
		this.gerechtid = gerechtid;
	}


	public String getNaam() {
		return naam;
	}


	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getGebruikerid() {
		return gebruikerid;
	}


	public void setGebruikerid(String gebruikerid) {
		this.gebruikerid = gebruikerid;
	}
}