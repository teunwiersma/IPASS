package nl.hu.ipass.spetterendeten.persistence;

import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;

public interface GerechtDAO {

	public boolean save(Gerecht gerecht);
	public List<openbaarGerecht> findAllOpenbaarGerecht(String gebruikerid);
	public boolean gerechtDelen(openbaarGerecht openbaarGerecht);
	public List<Gerecht> findAllIngredientenGerecht(String gebruikerid);
	public List<Gerecht> findAllNaamGerecht(String gebruikerid);
	public List<Gerecht> findNaamGerecht(String naamgerecht);
	public List<Gerecht> findNaamIngredient(int gerechtid);
}
