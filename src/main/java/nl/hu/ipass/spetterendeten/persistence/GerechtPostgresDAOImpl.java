package nl.hu.ipass.spetterendeten.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.ingredientGerecht;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;

public class GerechtPostgresDAOImpl extends PostgresBaseDAO implements GerechtDAO {

	private List<Gerecht> selectGerecht (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int gebruikerid = rs.getInt("gebruikerid");
				String naamingredient = rs.getString("naamingredient");

				Gerecht newGerecht = new Gerecht (naamingredient, gebruikerid);
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
				int gebruikerid = rs.getInt("gebruikerid");
				
				Gerecht newGerecht = new Gerecht ( gerechtid, naamgerecht, gebruikerid);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	
	private List<Gerecht> selectgerechtnaam (String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try (Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String naamgerecht = rs.getString("naamgerecht");
				int gerechtid = rs.getInt("gerechtid");
				Gerecht newGerecht = new Gerecht (naamgerecht, gerechtid);
				results.add(newGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	private List<Gerecht> selectingredientnaam(String query){
		List<Gerecht> results = new ArrayList<Gerecht>();
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String naamingredient = rs.getString("naamingredient");
				int gerechtid = rs.getInt("gerechtid");
				int eiwit = rs.getInt("eiwit");
				int energie = rs.getInt("energie");
				int water = rs.getInt("water");
				int koolhydraten = rs.getInt("koolhydraten");
				int suikers = rs.getInt("suikers");
				int vet = rs.getInt("vet");

				String naamgerecht = rs.getString("naamgerecht");
				
				Gerecht newingredient = new Gerecht( naamgerecht, gerechtid,   eiwit,  energie,  water,	 koolhydraten,  suikers,  vet , naamingredient);
				results.add(newingredient);
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	@Override
	public List<Gerecht> findNaamIngredient(int gerechtid){
		return selectingredientnaam("select i.naamingredient, i.eiwit,  i.energie, i.water, i.koolhydraten, i.suikers, i.vet, ig.gerechtid, g.naamgerecht from ingredientgerecht as ig join gerecht as g on ig.gerechtid = g.gerechtid join ingredient as i on i.ingredientid = ig.ingredientid where ig.gerechtid = " + gerechtid + ";");
	}
	
	@Override
	public List<Gerecht> findNaamGerecht(String naamgerecht) {
		return selectgerechtnaam("SELECT NAAMGERECHT, gerechtid FROM GERECHT WHERE NAAMGERECHT = '" + naamgerecht + "';");
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
			String query = "insert into gerecht(naamgerecht, gebruikerid) values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, gerecht.getNaamGerecht());
			stmt.setInt(2, gerecht.getGebruikerid());
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

	
	@Override 
	public boolean ingredientenGerechtOpslaan(ingredientGerecht ingredientGerecht) {
		try(Connection con = super.getConnection()){
			String query = "INSERT INTO INGREDIENTGERECHT(INGREDIENTID, GERECHTID) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, ingredientGerecht.getIngredientid());
			stmt.setInt(2, ingredientGerecht.getGerechtid());
			stmt.executeQuery();
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	

}
