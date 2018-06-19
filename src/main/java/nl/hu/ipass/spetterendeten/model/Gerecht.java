package nl.hu.ipass.spetterendeten.model;

public class Gerecht {


	private int gerechtid;
	private String naam;
	private int gebruikerid;
	
	
	
	public Gerecht(int gerechtid, String naam, int gebruikerid) {
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

	public int getGebruikerid() {
		return gebruikerid;
	}


	public void setGebruikerid(int gebruikerid) {
		this.gebruikerid = gebruikerid;
	}
}