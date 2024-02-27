package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.DimmerDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.models.Dimmer;
import com.smarthome.smarthome.repositories.DeviceRepository;
import com.smarthome.smarthome.repositories.DimmerRepository;
import com.smarthome.smarthome.services.interfaces.IDimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimmerService implements IDimmerService {
    @Autowired
    private DimmerRepository dimmerRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Dimmer createDimmer(DimmerDTO dimmerDTO) throws Exception {
        Device device = deviceRepository.findById(dimmerDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        if (dimmerRepository.findByDeviceId(device.getId()).size() >= device.getHasDimmer())
            throw new DataIntegrityViolationException("The number of device's dimmer is enough");
        Dimmer newDimmer = Dimmer.builder()
                .name(dimmerDTO.getName())
                .icon(dimmerDTO.getIcon())
                .device(device)
                .build();
        return dimmerRepository.save(newDimmer);
    }

    @Override
    public Dimmer updateDimmer(int id, DimmerDTO dimmerDTO) throws Exception {
        Dimmer dimmer = dimmerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find dimmer"));
        if(dimmer.getDevice().getId() != dimmerDTO.getDeviceId())
            throw new DataIntegrityViolationException("Cannot change device for this dimmer");
        Device device = deviceRepository.findById(dimmerDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find device"));
        dimmer.setName(dimmerDTO.getName());
        dimmer.setIcon(dimmerDTO.getIcon());
        dimmer.setDevice(device);
        return dimmerRepository.save(dimmer);
    }
    @Override
    public List<Dimmer> getAllDimmer() {
        return dimmerRepository.findAll();
    }
}
