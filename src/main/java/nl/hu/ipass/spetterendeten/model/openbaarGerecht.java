package nl.hu.ipass.spetterendeten.model;

public class openbaarGerecht {
	
	private int openbaarGerechtID;
	private int GerechtID;
	private int gebruikerid;

	public openbaarGerecht(int openbaarGerechtID, int gerechtID, int gebruikerid) {
		this.openbaarGerechtID = openbaarGerechtID;
		this.GerechtID = gerechtID;
		this.gebruikerid = gebruikerid;
	}

	public openbaarGerecht(int gerechtID, int gebruikerid) {
		this.GerechtID = gerechtID;
		this.gebruikerid = gebruikerid;
	}

	public int getOpenbaarGerechtID() {
		return openbaarGerechtID;
	}

	public void setOpenbaarGerechtID(int openbaarGerechtID) {
		this.openbaarGerechtID = openbaarGerechtID;
	}

	public int getGerechtID() {
		return GerechtID;
	}

	public void setGerechtID(int gerechtID) {
		GerechtID = gerechtID;
	}

	public int getGebruikerid() {
		return gebruikerid;
	}

	public void setGebruikerid(int gebruikerid) {
		this.gebruikerid = gebruikerid;
	}
}
