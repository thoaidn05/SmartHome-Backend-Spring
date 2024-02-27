package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    boolean existsByName(String name);
}
