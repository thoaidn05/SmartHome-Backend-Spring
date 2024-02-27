package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.DsensorDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Asensor;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.models.Dsensor;
import com.smarthome.smarthome.repositories.DeviceRepository;
import com.smarthome.smarthome.repositories.DsensorRepository;
import com.smarthome.smarthome.services.interfaces.IDsensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DsensorService implements IDsensorService {
    @Autowired
    private DsensorRepository dsensorRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Dsensor createDsensor(DsensorDTO dsensorDTO) throws Exception {
        Device device = deviceRepository.findById(dsensorDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        if (dsensorRepository.findByDeviceId(device.getId()).size() >= device.getHasDsensor())
            throw new DataIntegrityViolationException("The number of device's dsensor is enough");
        Dsensor newDsensor = Dsensor.builder()
                .name(dsensorDTO.getName())
                .icon(dsensorDTO.getIcon())
                .device(device)
                .build();
        return dsensorRepository.save(newDsensor);
    }

    @Override
    public Dsensor updateDsensor(int id, DsensorDTO dsensorDTO) throws Exception {
        Dsensor existingDsensor = dsensorRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find dsensor"));
        if(existingDsensor.getDevice().getId() != dsensorDTO.getDeviceId())
            throw new DataIntegrityViolationException("Cannot change device for this dsensor");
        Device device = deviceRepository.findById(dsensorDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        existingDsensor.setName(dsensorDTO.getName());
        existingDsensor.setIcon(dsensorDTO.getIcon());
        existingDsensor.setDevice(device);
        return dsensorRepository.save(existingDsensor);
    }
    @Override
    public List<Dsensor> getAllDsensor() {
        return dsensorRepository.findAll();
    }
}
