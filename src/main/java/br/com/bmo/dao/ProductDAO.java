package br.com.bmo.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.bmo.model.Category;
import br.com.bmo.model.Product;

public class ProductDAO {

	private Connection connection;

	public ProductDAO(Connection connection) {
		this.connection = connection;
	}

	public void save(Product product) {

		String sql = "INSERT INTO product (name, description, price, category_id) VALUES (?, ?, ?, ?)";

		// try-with-resources
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			connection.setAutoCommit(false);
			
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setBigDecimal(3, product.getPrice());
			stmt.setInt(4, product.getCategory().getId());
			stmt.execute();
			
			connection.commit();

			try (ResultSet rst = stmt.getGeneratedKeys()) {
				while (rst.next()) {
					product.setId(rst.getInt(1));
					System.out.println("product created - id: " + product.getId());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				System.out.println("ROLLBACK EXECUTED");
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	public List<Product> getList() {
		List<Product> products = new ArrayList<Product>();
		
		String sql = "SELECT P.id, P.name, P.description, P.price, C.id, C.name FROM product P"
				+ " INNER JOIN category as C ON (C.id = P.category_id)";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.execute();
			
			try (ResultSet rst = pstmt.getResultSet()) {
				while (rst.next()) {
					Category currentCategory = new Category(rst.getInt(5), rst.getString(6));
					Product currentProduct = new Product(rst.getInt(1),
							rst.getString(2),
							rst.getString(3), 
							BigDecimal.valueOf(rst.getDouble(4)),
							currentCategory);
					
					products.add(currentProduct);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return products;
	}
	
	public Product selectBy(Integer id) {
		
		Product product = null;
		
		String sql = "SELECT P.id, P.name, P.description, P.price, C.id, C.name FROM product P"
				+ " INNER JOIN category C on (P.category_id = C.id)"
				+ " WHERE P.id = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.execute();
			
			ResultSet rst = pstmt.getResultSet();
			while (rst.next()) {
				Category currentCategory = new Category(rst.getInt(5), rst.getString(6));
				product = new Product(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4), currentCategory);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return product;
	}

	public List<Product> selectBy(Category category) throws SQLException {
		List<Product> products = new ArrayList<>();
		
		String sql = "SELECT id, name, description, price "
				+ "FROM product WHERE category_id = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, category.getId());
			pstmt.execute();
			
			ResultSet rst = pstmt.getResultSet();
			while (rst.next()) {
				Product currentProduct = new Product(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getBigDecimal(4), category);
				products.add(currentProduct);
			}
		}
		
		return products;
	}
	
	public void delete(Product product) {
		String sql = "DELETE FROM product WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, product.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void update(Product product) throws SQLException {
		String sql = "UPDATE product SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setBigDecimal(3, product.getPrice());
			pstmt.setInt(4, product.getCategory().getId());
			pstmt.execute();
		}
	}
}
