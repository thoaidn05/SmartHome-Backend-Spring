package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.UserDeviceDTO;
import com.smarthome.smarthome.models.UserDevice;

public interface IUserDeviceService {
    UserDevice addUserDevice(UserDeviceDTO userDeviceDTO) throws Exception;
}
