package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.UserAsensor;
import com.smarthome.smarthome.models.UserDsensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDsensorRepository extends JpaRepository<UserDsensor, Integer> {

}
