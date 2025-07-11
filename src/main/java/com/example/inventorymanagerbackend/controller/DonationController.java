package com.example.inventorymanagerbackend.controller;

import com.example.inventorymanagerbackend.entity.Distribution;
import com.example.inventorymanagerbackend.entity.Donation;
import com.example.inventorymanagerbackend.service.DonationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/donate")
    public void addDonation(@RequestBody Donation donation) {
        donationService.saveDonation(donation);
    }

    @PostMapping("/distribute")
    public void addDistribution(@RequestBody Distribution distribution) {
        donationService.saveDistribution(distribution);
    }

    @GetMapping("/reports/inventory")
    public Map<String, Integer> getInventoryReport() {
        return donationService.getInventoryReport();
    }

    @GetMapping("/reports/donors")
    public Map<String, Map<String, Integer>> getDonorsReport() {
        return donationService.getDonorReport();
    }

}
