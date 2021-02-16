package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bmo.model.Category;

public class CategoryDAO {

	private Connection connection;

	public CategoryDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Category> getList() {
		List<Category> categories = new ArrayList<>();
		
		String sql = "SELECT id, name FROM category";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.execute();
			ResultSet rst = pstmt.getResultSet();
			
			while (rst.next()) {
				Category currentCategory = new Category(rst.getInt(1), rst.getString(2));
				categories.add(currentCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return categories;
	}
	
}
