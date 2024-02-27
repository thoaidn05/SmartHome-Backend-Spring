package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.AsensorDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.models.Asensor;
import com.smarthome.smarthome.models.Switch;
import com.smarthome.smarthome.repositories.DeviceRepository;
import com.smarthome.smarthome.repositories.AsensorRepository;
import com.smarthome.smarthome.services.interfaces.IAsensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsensorService implements IAsensorService {
    @Autowired
    private AsensorRepository asensorRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Asensor createAsensor(AsensorDTO asensorDTO) throws Exception {
        Device device = deviceRepository.findById(asensorDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        if (asensorRepository.findByDeviceId(device.getId()).size() >= device.getHasAsensor())
            throw new DataIntegrityViolationException("The number of device's asensor is enough");
        Asensor newAsensor = Asensor.builder()
                .name(asensorDTO.getName())
                .icon(asensorDTO.getIcon())
                .unit(asensorDTO.getUnit())
                .device(device)
                .build();
        return asensorRepository.save(newAsensor);
    }

    @Override
    public Asensor updateAsensor(int id, AsensorDTO asensorDTO) throws Exception {
        Asensor existingAsensor = asensorRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find asensor"));
        if(existingAsensor.getDevice().getId() != asensorDTO.getDeviceId())
            throw new DataIntegrityViolationException("Cannot change device for this asensor");
        Device device = deviceRepository.findById(asensorDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        existingAsensor.setName(asensorDTO.getName());
        existingAsensor.setIcon(asensorDTO.getIcon());
        existingAsensor.setUnit(asensorDTO.getUnit());
        existingAsensor.setDevice(device);
        return asensorRepository.save(existingAsensor);
    }
    @Override
    public List<Asensor> getAllAsensor() {
        return asensorRepository.findAll();
    }
}
