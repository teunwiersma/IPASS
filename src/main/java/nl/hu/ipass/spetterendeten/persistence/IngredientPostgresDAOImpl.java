package nl.hu.ipass.spetterendeten.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.spetterendeten.model.Ingredient;

public class IngredientPostgresDAOImpl extends PostgresBaseDAO implements IngredientDAO {
	
	private UserDao userdaoimpl = new UserPostgresDaoImpl();
	
	private List<Ingredient> selectIngredient (String query){
		List<Ingredient> results = new ArrayList<Ingredient>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int ingredientid = rs.getInt("ingredientid");
				String naam = rs.getString("naam");
				int energie = rs.getInt("energie");
				int water = rs.getInt("water");
				int eiwit = rs.getInt("eiwit");
				int koolhydraten = rs.getInt("koolhydraten");
				int suikers = rs.getInt("suikers");
				int vet = rs.getInt("vet");
				int gebruikerid = rs.getInt("gebruikerid");
				Ingredient newIngredient = new Ingredient( ingredientid, naam, energie,  water,  eiwit,  koolhydraten,  suikers,  vet,  gebruikerid);
				results.add(newIngredient);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	@Override
	public List<Ingredient> findAll(){
		//System.out.println(userdaoimpl.findGebruikerIDForUser("henk", "1234"));
		String gebruikerid = userdaoimpl.getGebruikerid() ; 
		//if(gebruikerid)
		return selectIngredient("SELECT * FROM Ingredient where gebruikerid = " +  gebruikerid  + " ORDER BY IngredientID DESC;");
	}
	
	@Override
	public boolean save(Ingredient Ingredient) {
		try (Connection connection = super.getConnection()) {
			String query = "insert into Ingredient(naam, gebruikerid, energie,  water,  eiwit,  koolhydraten,  suikers,  vet) values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, Ingredient.getNaam());
			stmt.setInt(2, Ingredient.getGebruikerid());
			stmt.setInt(3, Ingredient.getEnergie());
			stmt.setInt(4, Ingredient.getWater());
			stmt.setInt(5, Ingredient.getEiwit());
			stmt.setInt(6, Ingredient.getKoolhydraten());
			stmt.setInt(7, Ingredient.getSuikers());
			stmt.setInt(8, Ingredient.getVet());

			if(stmt.executeUpdate() ==1 ) {
				stmt.close();
				return true;
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public Ingredient findByID(int ingredientid) {
		Ingredient Ingredient = null;

		try (Connection connection = super.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Ingredient where ingredientid = '"+ ingredientid + "';");

			while (rs.next()) {
				Ingredient = new Ingredient(rs.getInt("ingredientid" ), rs.getString("naam"), rs.getInt("energie"), rs.getInt("water"), rs.getInt("eiwit"), rs.getInt("koolhydraten"), rs.getInt("suikers"), rs.getInt("vet"), rs.getInt("gebruikerid"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return Ingredient;
	}

}
