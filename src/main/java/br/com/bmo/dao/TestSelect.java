package br.com.bmo.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.bmo.model.Product;

public class TestSelect {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		ProductDAO productDAO = new ProductDAO(connectionFactory.getConnection());
		
		List<Product> products = productDAO.getList();
		products.stream().forEach(product -> System.out.println(product));
	}
}
