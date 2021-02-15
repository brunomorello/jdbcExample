package br.com.bmo.dao;

import java.sql.SQLException;

public class TestPoolConnections {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connFactory = new ConnectionFactory();
		
		for (int i = 0; i < 20; i++) {
			connFactory.getConnection();
			System.out.println("opening connection " + i );
		}
	}
}
