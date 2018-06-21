package nl.hu.ipass.spetterendeten.persistence;

import java.util.List;

import nl.hu.ipass.spetterendeten.model.Gerecht;

public interface GerechtDAO {

	public List<Gerecht> findAll(String gebruikerid);
	public boolean save(Gerecht gerecht);
	public Gerecht findByNaam(String naam);
}
