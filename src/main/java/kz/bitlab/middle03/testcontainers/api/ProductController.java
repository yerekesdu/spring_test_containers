package kz.bitlab.middle03.testcontainers.api;

import jakarta.validation.Valid;
import kz.bitlab.middle03.testcontainers.model.Product;
import kz.bitlab.middle03.testcontainers.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product productSaved = productService.saveProduct(product);
        return ResponseEntity.ok(productSaved);
    }

}
