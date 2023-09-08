package com.enviro.assessment.grad001.christiantshukudu.investment.Controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.enviro.assessment.grad001.christiantshukudu.investment.Repo.ProductRepository;
import com.enviro.assessment.grad001.christiantshukudu.investment.Model.Product;
import java.math.BigDecimal;

// The @RestController annotation indicates that this class is a controller that handles HTTP requests.
@RestController
@RequestMapping("/api/products")
public class ProductController {
    // Injecting dependencies using constructor injection.
    private final ProductRepository productRepository;
    private final WithdrawalService withdrawalService;

    @Autowired
    public ProductController(ProductRepository productRepository, WithdrawalService withdrawalService) {
        this.productRepository = productRepository;
        this.withdrawalService = withdrawalService;
    }

    // This method handles HTTP GET requests to "/api/products/{productId}".
    // It retrieves a product by its ID and returns it as a ResponseEntity.

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        // Use the ProductRepository to find a product by its ID
        Optional<Product> productOptional = productRepository.findById(productId);
        // If a product with the given ID is found, return a ResponseEntity with a
        // status code of 200 (OK)
        // and the product object as the response body.
        // If no product is found, return a ResponseEntity with a status code of 404
        // (Not Found).
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // This method handles HTTP POST requests to
    // "/api/products/{productId}/withdraw".
    // It creates a withdrawal notice for a product with the specified amount.
    @PostMapping("/{productId}/withdraw")
    public ResponseEntity<String> createWithdrawalNotice(
            @PathVariable Long productId,
            @RequestParam BigDecimal amount) {
        // Call the withdrawalService to attempt a withdrawal for the given product and
        // amount.
        if (withdrawalService.withdrawAmount(productId, amount)) {
            // If the withdrawal is successful, return a ResponseEntity with a status code
            // of 200 (OK)
            // and a message indicating a successful withdrawal.
            return ResponseEntity.ok("Withdrawal successful.");
        } else {
            // If the withdrawal amount exceeds the allowed limit or encounters any other
            // issue,
            // return a ResponseEntity with a status code of 400 (Bad Request) and an error
            // message.
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds the allowed limit.");
        }
    }
}
