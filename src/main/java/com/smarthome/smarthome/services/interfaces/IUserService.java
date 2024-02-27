package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.UserDTO;
import com.smarthome.smarthome.dtos.UserLoginDTO;
import com.smarthome.smarthome.models.User;


public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(UserLoginDTO userLoginDTO) throws Exception;
}
