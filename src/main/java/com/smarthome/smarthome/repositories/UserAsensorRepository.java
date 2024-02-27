package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.UserAsensor;
import com.smarthome.smarthome.models.UserSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAsensorRepository extends JpaRepository<UserAsensor, Integer> {

}
