package com.enviro.assessment.grad001.christiantshukudu.investment.Controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.enviro.assessment.grad001.christiantshukudu.investment.Repo.InvestorRepository;
import com.enviro.assessment.grad001.christiantshukudu.investment.Model.Investor;

// The @RestController annotation indicates that this class is a controller that handles HTTP requests.
@RestController
@RequestMapping("/api/investors")
public class InvestorController {
    // The constructor injection of the InvestorRepository dependency.
    // This allows Spring to automatically inject an instance of InvestorRepository
    // when creating an instance of this class.
    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorController(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    // This method handles HTTP GET requests to "/api/investors/{investorId}".
    // It retrieves an investor by their ID and returns it as a ResponseEntity.
    @GetMapping("/{investorId}")
    public ResponseEntity<Investor> getInvestor(@PathVariable Long investorId) {
        Optional<Investor> investorOptional = investorRepository.findById(investorId);
        return investorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
