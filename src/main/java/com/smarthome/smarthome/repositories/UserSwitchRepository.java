package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.UserDimmer;
import com.smarthome.smarthome.models.UserSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSwitchRepository extends JpaRepository<UserSwitch, Integer> {

}
