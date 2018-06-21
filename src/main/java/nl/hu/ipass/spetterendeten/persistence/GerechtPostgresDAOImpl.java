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
				int gerechtid = rs.getInt("gerechtid");
				String naam = rs.getString("naam");
				String gebruikerid = rs.getString("gebruikerid");

				Gerecht newGerecht = new Gerecht( gerechtid,  naam,  gebruikerid);
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
				int openbaargerechtid = rs.getInt("openbaargerechtid" );
				int gerechtid = rs.getInt("gerechtid");
				int gebruikerid = rs.getInt("gebruikerid" );
				
				openbaarGerecht newOpenbaarGerecht = new openbaarGerecht(openbaargerechtid, gerechtid, gebruikerid);
				results.add(newOpenbaarGerecht);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
		return results;
	}
	
	@Override 
	public List<openbaarGerecht> findAllOpenbaarGerecht(String gebruikerid){
		return SelectOpenbaarGerecht("SELECT * FROM OPENBAARGERECHT ORDER BY GERECHTID ASC");
	}
	
	@Override
	public List<Gerecht> findAll(String gebruikerid){
		
		return selectGerecht("SELECT * FROM GERECHT  where gebruikerid = " + gebruikerid + " ORDER BY GERECHTID ASC;");
	}
	
	
	
	@Override
	public boolean save(Gerecht gerecht) {
		try (Connection connection = super.getConnection()) {
			String query = "insert into gerecht(naam, gebruikerid) values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, gerecht.getNaam());
			stmt.setString(2, gerecht.getGebruikerid());
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
				gerecht = new Gerecht(resultset.getInt("gerechtid"), resultset.getString("naam"), resultset.getString("gebruikerid"));
			}
			resultset.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return gerecht;
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
