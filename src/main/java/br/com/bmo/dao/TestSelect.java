package br.com.bmo.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connFactory = new ConnectionFactory();
		Connection conn = connFactory.getConnection();
		
		Statement stmt = conn.createStatement();
		boolean result = stmt.execute("SELECT * FROM product");
		
		System.out.println("query executed? " + result);
		
		ResultSet resultSet = stmt.getResultSet();
		
		while (resultSet.next()) {
			Integer id = resultSet.getInt("id");
			System.out.println(id);
			String name = resultSet.getString("name");
			System.out.println(name);
			String description = resultSet.getString("description");
			System.out.println(description);
			BigDecimal price = resultSet.getBigDecimal("price");
			System.out.println(price.toString());
		}
		
		conn.close();
	}
}
