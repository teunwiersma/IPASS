package nl.hu.ipass.persistence;

import java.util.List;

import nl.hu.ipass.model.Gerecht;

public interface GerechtDAO {

	public List<Gerecht> findAll();
	public boolean save(Gerecht gerecht);
	public Gerecht findByNaam(String naam);
}
