package br.com.itau.dao;

public abstract class DAOFactory {
	public static final int MYSQL = 1;

	public abstract DestinationDAO getDestinationDAO();

	public static DAOFactory getDAOFactory(int type) {
		switch (type) {
		case MYSQL:
			return new ConnectionFactory();
		default:
			return null;
		}
	}

}
