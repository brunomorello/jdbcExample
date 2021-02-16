package br.com.bmo.controller;

import java.math.BigDecimal;
import java.util.List;

import br.com.bmo.dao.ConnectionFactory;
import br.com.bmo.dao.ProductDAO;
import br.com.bmo.model.Product;

public class ProductController {
	
	private ProductDAO productDAO;
	
	public ProductController() {
		this.productDAO = new ProductDAO(new ConnectionFactory().getConnection()); 
	}

	public void update(String name, String description, BigDecimal price, Integer id) {
		
	}

	public void delete(Integer id) {
		Product product = productDAO.selectBy(id);
		this.productDAO.delete(product);
	}

	public List<Product> list() {
		return this.productDAO.getList();
	}

	public void save(Product product) {
		if (product.getId() != 0) 
			this.productDAO.save(product);
		
	}

}
