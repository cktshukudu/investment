package com.enviro.assessment.grad001.christiantshukudu.investment.Repo;

import com.enviro.assessment.grad001.christiantshukudu.investment.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is a Spring Data JPA repository interface for the Product entity.
// By extending JpaRepository, it inherits built-in methods for CRUD operations on Investor objects.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}