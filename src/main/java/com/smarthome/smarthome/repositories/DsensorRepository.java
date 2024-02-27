package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Dsensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DsensorRepository extends JpaRepository<Dsensor, Integer> {
    List<Dsensor> findByDeviceId(int deviceId);
}
