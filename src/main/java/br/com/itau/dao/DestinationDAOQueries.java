package br.com.itau.dao;

public class DestinationDAOQueries {
	
	public static final String selectCoordinatesByCityName = "SELECT ID, NAME, LATITUDE, LONGITUDE FROM CITY WHERE NAME IN (?,?)";
	
}
