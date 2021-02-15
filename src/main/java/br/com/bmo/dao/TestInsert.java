package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connFactory = new ConnectionFactory();
		Connection conn = connFactory.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO product (name, description, price)"
				+ " VALUES ('KEYBOARD', 'bluetooth keyboard', 50.00)",
				Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rst = stmt.getGeneratedKeys();
		
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("product created - id: " + id);
		}
		
	}
}
