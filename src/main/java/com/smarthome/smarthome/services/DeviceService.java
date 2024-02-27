package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.DeviceDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.helpers.FileHelper;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.repositories.DeviceRepository;
import com.smarthome.smarthome.services.interfaces.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements IDeviceService {
    @Value("${file_storage.device_image}")
    private String uploadFolder;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device createDevice(DeviceDTO deviceDTO) throws Exception {
        if (deviceRepository.existsByName(deviceDTO.getName()))
            throw new DataIntegrityViolationException("Device already exists");
        Device newDevice = Device.builder()
                .name(deviceDTO.getName())
                .image(FileHelper.storeImage(deviceDTO.getImage(), uploadFolder))
                .zigbeeRoleEnum(deviceDTO.getZigbeeRole())
                .hasDimmer(deviceDTO.getHasDimmer())
                .hasAsensor(deviceDTO.getHasAsensor())
                .hasDsensor(deviceDTO.getHasDsensor())
                .hasSwitch(deviceDTO.getHasSwitch())
                .build();
        return deviceRepository.save(newDevice);
    }

    @Override
    public Device updateDevice(int id, DeviceDTO deviceDTO) throws Exception {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        device.setImage(FileHelper.storeImage(deviceDTO.getImage(), uploadFolder));
        device.setName(deviceDTO.getName());
        device.setZigbeeRoleEnum(deviceDTO.getZigbeeRole());
        device.setHasDimmer(deviceDTO.getHasDimmer());
        device.setHasAsensor(deviceDTO.getHasAsensor());
        device.setHasDsensor(deviceDTO.getHasDsensor());
        device.setHasSwitch(deviceDTO.getHasSwitch());
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> getAllDevice() {
        return deviceRepository.findAll();
    }

}
