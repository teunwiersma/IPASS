package nl.hu.ipass.spetterendeten.model;

public class User {
	
	private String wachtwoord;
	private String gebruikersnaam;
	private String gebruikerid;
	
	public User(String wachtwoord , String gebruikersnaam , String gebruikerid ) {
		this.wachtwoord = wachtwoord;
		this.gebruikersnaam = gebruikersnaam;
		this.gebruikerid = gebruikerid;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getGebruikerid() {
		return gebruikerid;
	}

	public void setGebruikerid(String gebruikerid) {
		this.gebruikerid = gebruikerid;
	}
	
	

}
