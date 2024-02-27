package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Switch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwitchRepository extends JpaRepository<Switch, Integer> {
    List<Switch> findByDeviceId(int deviceId);
}
