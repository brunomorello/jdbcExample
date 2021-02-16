package br.com.bmo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.bmo.model.Product;

public class TestInsert {
	public static void main(String[] args) throws SQLException {

		ConnectionFactory connFactory = new ConnectionFactory();
		ProductDAO productDAO = new ProductDAO(connFactory.getConnection());
		productDAO.save(new Product("WEBCAM", "1040p resolution", BigDecimal.valueOf(240.0)));
		
		List<Product> products = productDAO.getList();
		products.stream().forEach(product -> System.out.println(product));
		
	}
}
