package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Dimmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DimmerRepository extends JpaRepository<Dimmer, Integer> {
    List<Dimmer> findByDeviceId(int deviceId);
}
