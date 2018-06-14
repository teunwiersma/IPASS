package nl.hu.ipass.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.model.Gerecht;

public class GerechtPostgresDAOImpl extends PostgresBaseDAO implements GerechtDAO {

	private List<Gerecht> selectGerecht (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int gerechtid = rs.getInt("gerechtid");
				String naam = rs.getString("naam");
				int ingredientid = rs.getInt("ingredientid");
				int gebruikerid = rs.getInt("gebruikerid");
				String ingredient1 = rs.getString("ingredient1");
				String ingredient2 = rs.getString("ingredient2");
				String ingredient3 = rs.getString("ingredient3");
				String ingredient4 = rs.getString("ingredient4");
				String ingredient5 = rs.getString("ingredient5");
				String ingredient6 = rs.getString("ingredient6");
				String ingredient7 = rs.getString("ingredient7");
				String ingredient8 = rs.getString("ingredient8");
				String ingredient9 = rs.getString("ingredient9");
				String ingredient10 = rs.getString("ingredient10");
				String ingredient11 = rs.getString("ingredient11");
				String ingredient12 = rs.getString("ingredient12");
				String ingredient13 = rs.getString("ingredient13");
				String ingredient14 = rs.getString("ingredient14");
				String ingredient15 = rs.getString("ingredient15");
				String ingredient16 = rs.getString("ingredient16");

				Gerecht newGerecht = new Gerecht( gerechtid,  naam,  ingredientid,  gebruikerid,  ingredient1, ingredient2,  ingredient3,  ingredient4,  ingredient5,  ingredient6,
						 ingredient7,  ingredient8,  ingredient9,  ingredient10,  ingredient11,
						 ingredient12,  ingredient13,  ingredient14,  ingredient15,  ingredient16);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	@Override
	public List<Gerecht> findAll(){
		return selectGerecht("SELECT GERECHTID, NAAM, INGREDIENTID, INGREDIENTEN, GEBRUIKERID FROM GERECHT ORDER BY GERECHTID DESC;");
	}
	
	@Override
	public boolean save(Gerecht gerecht) {
		try (Connection connection = super.getConnection()) {
			String query = "insert into gerecht(naam, ingredientid, gebruikerid, ingredient1, ingredient2,  ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10, ingredient11,  ingredient12, ingredient13, ingredient14, ingredient15, ingredient16,) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, gerecht.getNaam());
			stmt.setInt(2, gerecht.getIngredientid());
			stmt.setInt(3, gerecht.getGebruikerid());
			stmt.setString(4, gerecht.getIngredient1());
			stmt.setString(5, gerecht.getIngredient2());
			stmt.setString(6, gerecht.getIngredient3());
			stmt.setString(7, gerecht.getIngredient4());
			stmt.setString(8, gerecht.getIngredient5());
			stmt.setString(9, gerecht.getIngredient6());
			stmt.setString(10, gerecht.getIngredient7());
			stmt.setString(11, gerecht.getIngredient8());
			stmt.setString(12, gerecht.getIngredient9());
			stmt.setString(13, gerecht.getIngredient10());
			stmt.setString(14, gerecht.getIngredient11());
			stmt.setString(15, gerecht.getIngredient12());
			stmt.setString(16, gerecht.getIngredient13());
			stmt.setString(17, gerecht.getIngredient14());
			stmt.setString(18, gerecht.getIngredient15());
			stmt.setString(19, gerecht.getIngredient16());
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
				gerecht = new Gerecht(resultset.getInt("gerechtid"), resultset.getString("naam"), resultset.getInt("ingredientid"), resultset.getInt("gebruikerid"),
						resultset.getString("ingredient1"),	resultset.getString("ingredient2"), resultset.getString("ingredient3"), resultset.getString("ingredient4"),
						resultset.getString("ingredient5"),	resultset.getString("ingredient6"), resultset.getString("ingredient7"), resultset.getString("ingredient8"),
						resultset.getString("ingredient9"), resultset.getString("ingredient10"), resultset.getString("ingredient11"), resultset.getString("ingredient12"),
						resultset.getString("ingredient13"),resultset.getString("ingredient14"), resultset.getString("ingredient15"), resultset.getString("ingredient16") );
			}
			resultset.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return gerecht;
	}

}
