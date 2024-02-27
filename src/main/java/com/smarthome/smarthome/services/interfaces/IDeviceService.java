package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.DeviceDTO;
import com.smarthome.smarthome.models.Device;

import java.util.List;

public interface IDeviceService {
    Device createDevice(DeviceDTO deviceDTO) throws Exception;
    Device updateDevice(int id, DeviceDTO deviceDTO) throws Exception;
    List<Device> getAllDevice();
}

