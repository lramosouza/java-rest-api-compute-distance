package br.com.itau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.itau.model.City;
import br.com.itau.model.DistanceRequest;
import br.com.itau.util.DistanceAPIUtil;

public class DestinationDAOImpl implements DestinationDAO {

	Connection cn = null;
	PreparedStatement stmt;
	ResultSet rs = null;

	@Override
	public List<City> getCoordinatesByCityName(DistanceRequest distanceRequest) throws Exception {

		List<City> cities = new ArrayList<City>();

		try {
			cn = ConnectionFactory.createConnection();
			
			stmt = cn.prepareStatement(DestinationDAOQueries.selectCoordinatesByCityName);
			stmt.setString(1, distanceRequest.getSource());
			stmt.setString(2, distanceRequest.getDestination());
			
			rs = stmt.executeQuery();
			
			mapResultSet(cities);

		} catch (Exception e) {
			throw e;
		} finally {
			closeAllConnections();
		}
		
		if (cities.size() < 2){
			throw new Exception(DistanceAPIUtil.loadErrorMessage("cityNotFound"));
		}else{
			return cities;
		}
	}

	private void mapResultSet(List<City> cities) throws SQLException {
		while (rs.next()) {
				cities.add(
				new City(rs.getInt("ID"), 
						rs.getString("NAME"), 
						rs.getDouble("LATITUDE"), 
						rs.getDouble("LONGITUDE")));
		}
	}

	private void closeAllConnections() throws SQLException {
		if(cn != null){
			cn.close();
		}
		if (stmt != null){
			stmt.close();
		}
		if (rs != null){
			rs.close();
		}
	}

}
