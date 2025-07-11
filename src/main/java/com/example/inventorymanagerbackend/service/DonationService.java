package com.example.inventorymanagerbackend.service;

import com.example.inventorymanagerbackend.entity.Distribution;
import com.example.inventorymanagerbackend.entity.Donation;
import com.example.inventorymanagerbackend.repository.DistributionRepository;
import com.example.inventorymanagerbackend.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final DistributionRepository distributionRepository;

    public DonationService(DonationRepository donationRepository, DistributionRepository distributionRepository) {
        this.donationRepository = donationRepository;
        this.distributionRepository = distributionRepository;
    }

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public void saveDistribution(Distribution distribution) {
        distributionRepository.save(distribution);
    }

    public Map<String, Integer> getInventoryReport() {
//        Map<String, Integer> received = donationRepository.findAll()
//                .stream()
//                .collect(
//                    Collectors.groupingBy(
//                        Donation::getDonationType,
//                        Collectors.summingInt(
//                            Donation::getDonationAmount
//                        )
//                    )
//                );
//        Map<String, Integer> distributed = distributionRepository.findAll()
//                .stream()
//                .collect(
//                    Collectors.groupingBy(
//                        Distribution::getDistributionType,
//                        Collectors.summingInt(
//                            Distribution::getDistributionAmount
//                        )
//                    )
//                );
        Map<String, Integer> inventory = new HashMap<>();

        List<Object[]> received = donationRepository.getDonationSumByType();
        for (Object[] obj : received) {
            String type = (String) obj[0];
            int amount = ((Number) obj[1]).intValue();
            inventory.put(type, amount);
        }

        List<Object[]> distributed = distributionRepository.getDistributionSumByType();
        for (Object[] obj : distributed) {
            String type = (String) obj[0];
            int amount = ((Number) obj[1]).intValue();
            inventory.put(type, inventory.getOrDefault(type, 0) - amount);
        }

        return inventory;
    }

    public Map<String, Map<String, Integer>> getDonorReport() {
        List<Object[]> donorData = donationRepository.getDonationSumByDonorAndType();
        Map<String, Map<String, Integer>> report = new HashMap<>();

        for (Object[] obj : donorData) {
            String donorName = (String) obj[0];
            String donationType = (String) obj[1];
            int amount = ((Number) obj[2]).intValue();

            report.putIfAbsent(donorName, new HashMap<>());
            report.get(donorName).put(donationType, amount);
        }
        return report;
    }
}
