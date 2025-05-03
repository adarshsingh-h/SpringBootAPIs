package com.example.demo.repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public long countProducts() {
    String sql = "SELECT COUNT(*) FROM PRODUCT";
    return (Long) entityManager.createNativeQuery(sql).getSingleResult();
  }
}
