package com.electrolux.appliance.repository;

import com.electrolux.appliance.repository.entity.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    List<Appliance> findApplianceByApplianceIdIn(List<String> applianceIds);
}
