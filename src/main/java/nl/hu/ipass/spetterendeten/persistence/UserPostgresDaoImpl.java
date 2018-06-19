package nl.hu.ipass.spetterendeten.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserPostgresDaoImpl extends PostgresBaseDAO implements UserDao {

	public String gebruikerid = null;
	public String findGebruikerIDForUser(String name, String pass) {

		try (Connection connection = super.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet resultset = stmt.executeQuery("Select * from gebruiker where gebruikersnaam='" + name + "' and wachtwoord='" + pass + "';");

			while (resultset.next()) {
				gebruikerid = (resultset.getString("gebruikerid"));
				System.out.println(gebruikerid);
			}
			resultset.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return gebruikerid;
	}
	
	public String getGebruikerid() {
		return "2";
	}
	
	
}