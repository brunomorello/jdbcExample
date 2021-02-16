package br.com.bmo.controller;

import java.sql.Connection;
import java.util.List;

import br.com.bmo.dao.CategoryDAO;
import br.com.bmo.dao.ConnectionFactory;
import br.com.bmo.model.Category;

public class CategoryController {
	
	private CategoryDAO categoryDAO;

	public CategoryController() {
		Connection connection = new ConnectionFactory().getConnection();
		this.categoryDAO = new CategoryDAO(connection);
	}

	public List<Category> list() {
		return categoryDAO.getList();
	}

}
