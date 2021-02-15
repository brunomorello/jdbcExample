package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connFactory = new ConnectionFactory();
		Connection conn = connFactory.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM product WHERE id > 3");
		
		Integer rowsAffected = stmt.getUpdateCount();
		
		System.out.println("rows affected " + rowsAffected);
		
	}
}
