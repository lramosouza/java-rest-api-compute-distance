
package br.com.itau.dao;

import java.util.List;

import br.com.itau.model.City;
import br.com.itau.model.DistanceRequest;

public interface DestinationDAO {
	
	public List<City> getCoordinatesByCityName(DistanceRequest distanceRequest) throws Exception;

}
