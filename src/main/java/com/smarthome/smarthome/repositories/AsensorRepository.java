package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Asensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsensorRepository extends JpaRepository<Asensor, Integer> {
    List<Asensor> findByDeviceId(int deviceId);
}
