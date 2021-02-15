package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connFactory = new ConnectionFactory();
		Connection conn = connFactory.getConnection();
		
		String sql = "DELETE FROM product WHERE id > ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 2);
		
		stmt.execute();
		
		Integer rowsAffected = stmt.getUpdateCount();
		
		System.out.println("rows affected " + rowsAffected);
		
	}
}
