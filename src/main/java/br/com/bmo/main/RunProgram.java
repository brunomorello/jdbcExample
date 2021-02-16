package br.com.bmo.main;

import javax.swing.JFrame;

import br.com.bmo.view.ProductCategoryFrame;

public class RunProgram {
	public static void main(String[] args) {
		ProductCategoryFrame productCategory = new ProductCategoryFrame();
		productCategory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
