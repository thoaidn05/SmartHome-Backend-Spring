package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.SwitchDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.models.Dimmer;
import com.smarthome.smarthome.models.Switch;
import com.smarthome.smarthome.repositories.DeviceRepository;
import com.smarthome.smarthome.repositories.SwitchRepository;
import com.smarthome.smarthome.services.interfaces.ISwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SwitchService implements ISwitchService {
    @Autowired
    private SwitchRepository switchRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Switch createSwitch(SwitchDTO switchDTO) throws Exception {
        Device device = deviceRepository.findById(switchDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        if (switchRepository.findByDeviceId(device.getId()).size() >= device.getHasSwitch())
            throw new DataIntegrityViolationException("The number of device's switch is enough");
        Switch newSwitch = Switch.builder()
                .name(switchDTO.getName())
                .icon(switchDTO.getIcon())
                .device(device)
                .build();
        return switchRepository.save(newSwitch);
    }

    @Override
    public Switch updateSwitch(int id, SwitchDTO switchDTO) throws Exception {
        Switch existingSwitch = switchRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find switch"));
        if(existingSwitch.getDevice().getId() != switchDTO.getDeviceId())
            throw new DataIntegrityViolationException("Cannot change device for this switch");
        Device device = deviceRepository.findById(switchDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        existingSwitch.setName(switchDTO.getName());
        existingSwitch.setIcon(switchDTO.getIcon());
        existingSwitch.setDevice(device);
        return switchRepository.save(existingSwitch);
    }
    @Override
    public List<Switch> getAllSwitch() {
        return switchRepository.findAll();
    }
}
