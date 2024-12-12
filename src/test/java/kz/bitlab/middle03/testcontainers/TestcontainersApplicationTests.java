package kz.bitlab.middle03.testcontainers;

import kz.bitlab.middle03.testcontainers.api.ProductController;
import kz.bitlab.middle03.testcontainers.model.Product;
import kz.bitlab.middle03.testcontainers.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"classpath:/database.product/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/database.product/clear.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TestcontainersApplicationTests extends AbstractTestIntegration{


	@Autowired
	private ProductController productController;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testAllProducts() {
		List<Product> allProducts = productController.getAllProducts();
		assertNotNull(allProducts);
		assertFalse(allProducts.isEmpty());
		assertTrue(allProducts.size() >= 5);
	}

	@Test
	public void testProductCreate() {
		Product product = new Product();
		product.setName("Fanta");
		product.setPrice(550);

		ResponseEntity<Product> response = productController.createProduct(product);
		assertNotNull(response);
		assertEquals(550, Objects.requireNonNull(response.getBody()).getPrice());
		assertEquals("Fanta", response.getBody().getName());
	}

	@Test
	public void testProductInsert() {
		Product product = new Product();
		product.setName("Orange Juice");
		product.setPrice(220);

		Product savedProduct = productRepository.save(product);
		System.out.println("Saved product: " + savedProduct);
		assertNotNull(savedProduct);
		assertNotNull(product.getId());
		assertEquals("Orange Juice", savedProduct.getName());
		assertEquals(220, savedProduct.getPrice());
	}

}
