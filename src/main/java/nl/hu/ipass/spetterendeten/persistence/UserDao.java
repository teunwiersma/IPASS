package nl.hu.ipass.spetterendeten.persistence;



public interface UserDao {
	
	public String findGebruikerIDForUser(String name, String pass);

}
