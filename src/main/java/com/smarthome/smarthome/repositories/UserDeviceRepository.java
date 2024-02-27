package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {
}
