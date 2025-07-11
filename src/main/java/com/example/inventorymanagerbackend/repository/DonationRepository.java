package com.example.inventorymanagerbackend.repository;

import com.example.inventorymanagerbackend.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    // Additional query methods can be defined here if needed
    @Query("SELECT do.donationType, SUM(do.donationAmount) FROM Donation do GROUP BY do.donationType")
    List<Object[]> getDonationSumByType();

    @Query("SELECT do.donorName, do.donationType, SUM(do.donationAmount) FROM Donation do GROUP BY do.donorName, do.donationType")
    List<Object[]> getDonationSumByDonorAndType();

}
