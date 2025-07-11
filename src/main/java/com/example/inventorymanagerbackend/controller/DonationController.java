package com.example.inventorymanagerbackend.controller;

import com.example.inventorymanagerbackend.entity.Distribution;
import com.example.inventorymanagerbackend.entity.Donation;
import com.example.inventorymanagerbackend.service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @Operation(summary = "Add a new donation")
    @PostMapping("/donate")
    public void addDonation(@RequestBody Donation donation) {
        donationService.saveDonation(donation);
    }

    @Operation(summary = "Add a new distribution")
    @PostMapping("/distribute")
    public void addDistribution(@RequestBody Distribution distribution) {
        donationService.saveDistribution(distribution);
    }

    @Operation(summary = "Get inventory report (Total Amount for each type)")
    @GetMapping("/reports/inventory")
    public Map<String, Integer> getInventoryReport() {
        return donationService.getInventoryReport();
    }

    @Operation(summary = "Get donor report (By person and type)")
    @GetMapping("/reports/donors")
    public Map<String, Map<String, Integer>> getDonorsReport() {
        return donationService.getDonorReport();
    }

}
