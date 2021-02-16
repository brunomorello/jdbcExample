package br.com.bmo.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.bmo.model.Category;

public class TestSelectCategories {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		CategoryDAO categoryDAO = new CategoryDAO(connectionFactory.getConnection());
		
		List<Category> categoriesList = categoryDAO.getList();
		categoriesList.stream().forEach(category -> System.out.println(category.getName()));
		
	}
}
