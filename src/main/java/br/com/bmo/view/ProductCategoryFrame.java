package br.com.bmo.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.bmo.controller.CategoryController;
import br.com.bmo.controller.ProductController;
import br.com.bmo.model.Category;
import br.com.bmo.model.Product;

public class ProductCategoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelName, labelDescription, labelPrice, labelCategory;
	private JTextField textName, textDescription, textPrice;
	private JComboBox<Category> comboCategory;
	private JButton saveBtn, editBtn, clearBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel model;
	private ProductController productController;
	private CategoryController categoryController;
	
	public ProductCategoryFrame() {
		super("Products");
		Container container = getContentPane();
		setLayout(null);
		
		this.categoryController = new CategoryController();
		this.productController = new ProductController();
		
		labelName = new JLabel("Product Name");
		labelDescription = new JLabel("Product Description");
		labelPrice = new JLabel("Price"); 
		labelCategory = new JLabel("Category");
		
		labelName.setBounds(10, 10, 240, 15);
		labelDescription.setBounds(10, 90, 240, 15);
		labelPrice.setBounds(10, 10, 240, 15);
		labelCategory.setBounds(10, 90, 240, 15);
		
		labelName.setForeground(Color.BLACK);
		labelDescription.setForeground(Color.BLACK);
		labelPrice.setForeground(Color.BLACK);
		labelCategory.setForeground(Color.BLACK);
		
		container.add(labelName);
		container.add(labelDescription);
		container.add(labelPrice);
		container.add(labelCategory);
		
		textName = new JTextField();
		textDescription = new JTextField();
		textPrice = new JTextField();
		comboCategory = new JComboBox<Category>();
		
		comboCategory.addItem(new Category(0, "Select"));
		List<Category> categories = this.listCategories();
		for (Category category : categories) {
			comboCategory.addItem(category);
		}
		
		textName.setBounds(10, 25, 265, 20);
		textDescription.setBounds(10, 65, 265, 20);
		textPrice.setBounds(10, 25, 265, 20);
		comboCategory.setBounds(10, 105, 265, 20);
		
		container.add(textName);
		container.add(textDescription);
		container.add(textPrice);
		container.add(comboCategory);
		
		saveBtn = new JButton("Save");
		clearBtn = new JButton("Clear");
		
		saveBtn.setBounds(10, 145, 80, 20);
		clearBtn.setBounds(100, 145, 80, 20);
		
		container.add(saveBtn);
		container.add(clearBtn);
		
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		
		model.addColumn("PRODUCT ID#");
		model.addColumn("Product Name");
		model.addColumn("Product Description");
		
		filloutTable();
		
		table.setBounds(10, 185, 760, 300);
		container.add(table);
		
		deleteBtn = new JButton("Delete");
		editBtn = new JButton("Edit");
		
		deleteBtn.setBounds(10, 500, 80, 20);
		editBtn.setBounds(100, 500, 80, 20);
		
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				clearTable();
				filloutTable();
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				clearTable();
				filloutTable();
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
				clearTable();
				filloutTable();
			}
		});
		
	}
	
	protected void clearTable() {
		model.getDataVector().clear();
	}

	private void filloutTable() {
		List<Product> products = listProducts();
		try {
			for (Product product : products) {
				model.addRow(new Object[] {
						product.getId(),
						product.getName(),
						product.getDescription(),
						product.getPrice().toString()
				});
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	private void edit() {
		Object lineObj = (Object) model.getValueAt(table.getSelectedRow(), table.getSelectedRow());
		if (lineObj instanceof Integer) {
			Integer id = (Integer) lineObj;
			String name = (String) model.getValueAt(table.getSelectedRow(), 1);
			String description = (String) model.getValueAt(table.getSelectedRow(), 2);
			BigDecimal price = (BigDecimal) BigDecimal.valueOf(Double.parseDouble((String) model.getValueAt(table.getSelectedRow(), 3)));
			this.productController.update(name, description, price, id);
		} else {
			JOptionPane.showMessageDialog(this, "Please, select an ID");
		}
	}
	
	private void delete() {
		Object lineObj = (Object) model.getValueAt(table.getSelectedRow(), table.getSelectedRow());
		if (lineObj instanceof Integer) {
			Integer id = (Integer) lineObj;
			this.productController.delete(id);
			model.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Product deleted!");
		} else {
			JOptionPane.showMessageDialog(this, "Please, select an ID");
		}
	}
	
	private void save() {
		if(!textName.getText().equals("") && !textDescription.getText().equals("") && !textPrice.getText().equals("")) {
			Product product = new Product(textName.getText(), textDescription.getText(), BigDecimal.valueOf(Double.parseDouble(textPrice.getText())));
			Category category = (Category) comboCategory.getSelectedItem();
			product.setCategory(category);
			this.productController.save(product);
			JOptionPane.showMessageDialog(this, "Product created!");
			this.clear();
		} else {
			JOptionPane.showMessageDialog(this, "Name, Description and Price must be informed");
		}
	}

	private List<Category> listCategories() {
		return this.categoryController.list();
	}
	
	private List<Product> listProducts() {
		return this.productController.list();
	}
	
	private void clear() {
		this.textName.setText("");
		this.textDescription.setText("");
		this.comboCategory.setSelectedIndex(0);
	}
}
