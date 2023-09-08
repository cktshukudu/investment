package com.enviro.assessment.grad001.christiantshukudu.investment.Controller;

import com.enviro.assessment.grad001.christiantshukudu.investment.Repo.ProductRepository;
import com.enviro.assessment.grad001.christiantshukudu.investment.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WithdrawalService {
    private final ProductRepository productRepository;

    @Autowired
    public WithdrawalService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    // Constructor injection to inject the ProductRepository dependency.

    // This method attempts a withdrawal for a product with the specified amount.
    public boolean withdrawAmount(Long productId, BigDecimal amount) {
        // Find the product by its ID.
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            BigDecimal currentBalance = product.getCurrentBalance();
            BigDecimal maxAllowedWithdrawal = currentBalance.multiply(new BigDecimal("0.9"));
            // Check if the withdrawal amount is within the allowed limit.
            if (amount.compareTo(maxAllowedWithdrawal) <= 0) {
                // Withdrawal is valid, update the balance
                BigDecimal newBalance = currentBalance.subtract(amount);
                product.setCurrentBalance(newBalance);
                productRepository.save(product);// Save the updated product to the database.
                return true;// Return true to indicate a successful withdrawal.
            }
        }
        return false; // Return false if the withdrawal is not valid or the product doesn't exist.
    }
}
