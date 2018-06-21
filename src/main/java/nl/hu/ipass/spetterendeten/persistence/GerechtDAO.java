package nl.hu.ipass.spetterendeten.persistence;

import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;
import nl.hu.ipass.spetterendeten.model.openbaarGerecht;

public interface GerechtDAO {

	public List<Gerecht> findAll(String gebruikerid);
	public boolean save(Gerecht gerecht);
	public Gerecht findByNaam(String naam);
	public List<openbaarGerecht> findAllOpenbaarGerecht(String gebruikerid);
	public boolean gerechtDelen(openbaarGerecht openbaarGerecht);
}
