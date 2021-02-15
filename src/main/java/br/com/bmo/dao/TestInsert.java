package br.com.bmo.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connFactory = new ConnectionFactory();
		Connection conn = connFactory.getConnection();
		conn.setAutoCommit(false);
		
		try {
			String sql = "INSERT INTO product (name, description, price) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			setSqlValues("KEYBOARD", "bluetooth wireless", BigDecimal.valueOf(50.0), stmt);
			setSqlValues("DISPLAY", "lg display", BigDecimal.valueOf(400.0), stmt);
			
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ROLLBACK EXECUTED");
			conn.rollback();
			conn.close();
		}
	}

	private static void setSqlValues(String name, String description, BigDecimal price, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setBigDecimal(3, price);
		
		if (name.equals("DISPLAY")) {
			throw new RuntimeException("Display cannot be inserted");
		}
		
		stmt.execute();
		
		ResultSet rst = stmt.getGeneratedKeys();
		
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("product created - id: " + id);
		}
	}
}
