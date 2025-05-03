package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return productService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    return productService.getById(id);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Product product) {
    return productService.create(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
    return productService.update(id, product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return productService.delete(id);
  }

  @GetMapping("/count")
  public ResponseEntity<?> countProducts() {
    long count = productService.getTotalProductCount();
    return ResponseEntity.ok(Map.of("count", count));
  }
}
