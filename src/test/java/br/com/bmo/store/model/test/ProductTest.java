package br.com.bmo.store.model.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.bmo.model.Product;

public class ProductTest {

	@Test
	public void test() {
		Product p = new Product("test", "description", BigDecimal.TEN);
		assertEquals("test", p.getName());
		assertEquals(BigDecimal.TEN, p.getPrice());
		assertEquals("description", p.getDescription());
	}

}
