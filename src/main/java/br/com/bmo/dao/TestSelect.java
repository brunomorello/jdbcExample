package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.bmo.model.Product;

public class TestSelect {
	public static void main(String[] args) throws SQLException {
		
		Connection connection = new ConnectionFactory().getConnection();
		ProductDAO productDAO = new ProductDAO(connection);
		
		List<Product> products = productDAO.getList();
		products.stream().forEach(product -> System.out.println(product));
	}
}
