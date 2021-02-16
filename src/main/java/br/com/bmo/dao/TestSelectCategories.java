package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.bmo.model.Category;
import br.com.bmo.model.Product;

public class TestSelectCategories {
	public static void main(String[] args) throws SQLException {
		
		Connection connection = new ConnectionFactory().getConnection();
		CategoryDAO categoryDAO = new CategoryDAO(connection);
		
		List<Category> categoriesList = categoryDAO.getList();
		categoriesList.stream().forEach(category -> System.out.println(category.getName()));
		
		Category tech = categoriesList.get(2);
		List<Product> techProducts = new ProductDAO(connection).selectBy(tech);
		techProducts.stream().forEach(product -> System.out.println(product));

	}
}
