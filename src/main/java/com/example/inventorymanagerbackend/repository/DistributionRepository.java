package com.example.inventorymanagerbackend.repository;

import com.example.inventorymanagerbackend.entity.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {
    // Additional query methods can be defined here if needed

    @Query("SELECT di.distributionType, SUM(di.distributionAmount) FROM Distribution di GROUP BY di.distributionType")
    List<Object[]> getDistributionSumByType();
}
