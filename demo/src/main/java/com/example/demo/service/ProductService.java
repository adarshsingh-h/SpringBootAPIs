package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public ResponseEntity<?> getAll() {
    List<Product> products = repository.findAll();
    if(products.isEmpty()) {
      return ResponseEntity.ok().body(Collections.singletonMap("message", "No products found"));
    }
    return ResponseEntity.ok(products);
  }

  public ResponseEntity<?> getById(Long id) {
    Optional<Product> product = repository.findById(id);
    if(product.isPresent()) {
      return ResponseEntity.ok(product.get());
    }
    return ResponseEntity.ok().body(Collections.singletonMap("message", "No product found"));
  }

  public ResponseEntity<?> create(Product product) {
    if(product.getName() == null || product.getPrice() == null) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Need to pass both name and price"));
    }
    repository.save(product);
    return ResponseEntity.ok(product);
  }

  public ResponseEntity<?> update(Long id, Product product) {
    Optional<Product> existing = repository.findById(id);
    if(existing.isEmpty()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Product doesn't exist!"));
    } else if(product.getName() == null || product.getPrice() == null) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Name or Price missing!"));
    }
    existing.get().setName(product.getName());
    existing.get().setPrice(product.getPrice());
    repository.save(existing.get());
    return ResponseEntity.ok(existing);
  }

  public ResponseEntity<?> delete(Long id) {
    Optional<Product> product = repository.findById(id);
    if(product.isEmpty()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Product doesn't exist!"));
    }
    repository.deleteById(id);
    return ResponseEntity.ok().body(Collections.singletonMap("message", "Product deleted successfully"));
  }

  public long getTotalProductCount() {
    return repository.countProducts();
  }
}
