package com.n01635700.n01635700_hirparatest3ims.repository;

import com.n01635700.n01635700_hirparatest3ims.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
