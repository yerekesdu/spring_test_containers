package kz.bitlab.middle03.testcontainers.service;

import kz.bitlab.middle03.testcontainers.model.Product;
import kz.bitlab.middle03.testcontainers.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        LOGGER.info("Saved product: {}", savedProduct);
        return savedProduct;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        LOGGER.info("Found {} products", productList.size());
        return productList;
    }

    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }
}
