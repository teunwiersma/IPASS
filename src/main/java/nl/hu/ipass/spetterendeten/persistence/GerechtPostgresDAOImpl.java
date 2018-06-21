package nl.hu.ipass.spetterendeten.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;

public class GerechtPostgresDAOImpl extends PostgresBaseDAO implements GerechtDAO {

	private List<Gerecht> selectGerecht (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String gebruikerid = rs.getString("gebruikerid");
				String naamingredient = rs.getString("naamingredient");

				Gerecht newGerecht = new Gerecht ( gebruikerid, naamingredient);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	private List<openbaarGerecht> SelectOpenbaarGerecht(String query){
		List<openbaarGerecht> results = new ArrayList<openbaarGerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String naamgerecht = rs.getString("naamgerecht" );
			
				
				openbaarGerecht newOpenbaarGerecht = new openbaarGerecht(naamgerecht);
				results.add(newOpenbaarGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	private List<Gerecht> selectNaamGerecht (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String naamgerecht = rs.getString("naamgerecht");
				int gerechtid = rs.getInt("gerechtid");
				String gebruikerid = rs.getString("gebruikerid");
				
				Gerecht newGerecht = new Gerecht ( gerechtid, naamgerecht, gebruikerid);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	@Override
	public List<Gerecht> findAllNaamGerecht(String gebruikerid){
		return selectNaamGerecht("select * from gerecht where gebruikerid = " + gebruikerid + ";");
	}
	
	@Override 
	public List<openbaarGerecht> findAllOpenbaarGerecht(String gebruikerid){
		return SelectOpenbaarGerecht("select g.naamgerecht from openbaargerecht as og join gerecht as g on g.gerechtid = og.gerechtid;");
	}
	
	@Override
	public List<Gerecht> findAllIngredientenGerecht(String gebruikerid){
	
		return selectGerecht("select g.gebruikerid, i.naamingredient from ingredientgerecht as ig join gerecht as g on g.gerechtid = ig.gerechtid join ingredient as i on i.ingredientid = ig.ingredientid where g.gebruikerid = " + gebruikerid + ";");
	}
	
	
	
	@Override
	public boolean save(Gerecht gerecht) {
		try (Connection connection = super.getConnection()) {
			String query = "insert into gerecht(naam, gebruikerid) values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, gerecht.getNaamGerecht());
			stmt.setString(2, gerecht.getGebruikerid());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
	
	
	@Override
	public boolean gerechtDelen(openbaarGerecht openbaargerecht) {
		try(Connection connection = super.getConnection()){
			String query ="INSERT INTO OPENBAARGERECHT(gerechtid, gebruikerid) VALUES (?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, openbaargerecht.getGerechtID());
			stmt.setInt(2, openbaargerecht.getGebruikerid());
			stmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}

	

}
