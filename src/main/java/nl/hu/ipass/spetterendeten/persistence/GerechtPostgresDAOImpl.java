package nl.hu.ipass.spetterendeten.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;

public class GerechtPostgresDAOImpl extends PostgresBaseDAO implements GerechtDAO {

	private List<Gerecht> selectGerecht (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int gerechtid = rs.getInt("gerechtid");
				String naam = rs.getString("naam");
				int gebruikerid = rs.getInt("gebruikerid");

				Gerecht newGerecht = new Gerecht( gerechtid,  naam,  gebruikerid);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	@Override
	public List<Gerecht> findAll(){
		return selectGerecht("SELECT * FROM GERECHT ORDER BY GERECHTID DESC;");
	}
	
	@Override
	public boolean save(Gerecht gerecht) {
		try (Connection connection = super.getConnection()) {
			String query = "insert into gerecht(naam, gebruikerid) values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, gerecht.getNaam());
			stmt.setInt(2, gerecht.getGebruikerid());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
	@Override
	public Gerecht findByNaam(String naam) {
		Gerecht gerecht = null;

		try (Connection connection = super.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet resultset = stmt.executeQuery("select * from gerecht where naam = '"+ naam + "';");

			while (resultset.next()) {
				gerecht = new Gerecht(resultset.getInt("gerechtid"), resultset.getString("naam"), resultset.getInt("gebruikerid"));
			}
			resultset.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return gerecht;
	}

}
