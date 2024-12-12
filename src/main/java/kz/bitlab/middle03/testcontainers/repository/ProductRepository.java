package kz.bitlab.middle03.testcontainers.repository;

import kz.bitlab.middle03.testcontainers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
