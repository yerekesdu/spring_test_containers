package kz.bitlab.middle03.testcontainers;

import kz.bitlab.middle03.testcontainers.model.Product;
import kz.bitlab.middle03.testcontainers.repository.ProductRepository;
import kz.bitlab.middle03.testcontainers.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTestProduct {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testCreateProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Fanta");
        product.setPrice(200);
        when(productRepository.save(any(Product.class))).thenReturn(product) ;

        Product createdProduct = productService.saveProduct(product);
        assertNotNull(createdProduct);
        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getPrice(), createdProduct.getPrice());
        verify(productRepository).save(product);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(
                new Product(1L, "Fanta", 400),
                new Product(2L, "Cola", 450)
        );
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertTrue(products.size() >= 2);
        verify(productRepository).findAll();
    }

    @Test
    void testGetOneProduct() {
        Long id = 5L;
        Product product = new Product(id, "Fanta", 400);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Optional<Product> productOptional = productService.getProductById(id);
        assertTrue(productOptional.isPresent());
        assertEquals(product, productOptional.get());
        verify(productRepository).findById(id);
    }
}
