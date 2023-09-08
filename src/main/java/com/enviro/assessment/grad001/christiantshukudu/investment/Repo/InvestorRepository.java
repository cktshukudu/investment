package com.enviro.assessment.grad001.christiantshukudu.investment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.grad001.christiantshukudu.investment.Model.Investor;

// This is a Spring Data JPA repository interface for the Investor entity.
// By extending JpaRepository, it inherits built-in methods for CRUD operations on Investor objects.
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
