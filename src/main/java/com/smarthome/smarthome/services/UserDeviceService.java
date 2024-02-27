package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.UserDeviceDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.*;
import com.smarthome.smarthome.repositories.*;
import com.smarthome.smarthome.services.interfaces.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeviceService implements IUserDeviceService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserDeviceRepository userDeviceRepository;
    @Autowired
    private DimmerRepository dimmerRepository;
    @Autowired
    private UserDimmerRepository userDimmerRepository;
    @Autowired
    private SwitchRepository switchRepository;
    @Autowired
    private UserSwitchRepository userSwitchRepository;
    @Autowired
    private AsensorRepository asensorRepository;
    @Autowired
    private UserAsensorRepository userAsensorRepository;
    @Autowired
    private DsensorRepository dsensorRepository;
    @Autowired
    private UserDsensorRepository userDsensorRepository;

    @Override
    public UserDevice addUserDevice(UserDeviceDTO userDeviceDTO) throws Exception {
        Device existingDevice = deviceRepository.findById(userDeviceDTO.getDeviceId()).orElseThrow(() -> new DataNotFoundException("Cannot find this device"));
        User existingUser = userRepository.findById(userDeviceDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Cannot find this user"));
        Category existingCategory = categoryRepository.findById(existingUser.getId()).orElseThrow(() -> new DataNotFoundException("Cannot find this category"));
        Room existingRoom = roomRepository.findByIdAndUserId(userDeviceDTO.getRoomId(), userDeviceDTO.getUserId());
        if(existingRoom == null)
            throw new DataNotFoundException("Cannot find this room");

        UserDevice newUserDevice = UserDevice.builder()
                .name(existingDevice.getName())
                .image(existingDevice.getImage())
                .zigbeeRoleEnum(existingDevice.getZigbeeRoleEnum())
                .device(existingDevice)
                .room(existingRoom)
                .user(existingUser)
                .build();
        UserDevice exUserDevice = userDeviceRepository.save(newUserDevice);

        List<Dimmer> existingDimmer = dimmerRepository.findByDeviceId(existingDevice.getId());
        for (Dimmer dimmer : existingDimmer) {
            UserDimmer newUserDimmer = UserDimmer.builder()
                    .name(dimmer.getName())
                    .icon(dimmer.getIcon())
                    .defaultValue(80.0)
                    .userDevice(exUserDevice)
                    .room(existingRoom)
                    .category(existingCategory)
                    .build();
            userDimmerRepository.save(newUserDimmer);
        }

        List<Switch> existingSwitch = switchRepository.findByDeviceId(existingDevice.getId());
        for (Switch eachSwitch : existingSwitch) {
            UserSwitch newUserSwitch = UserSwitch.builder()
                    .name(eachSwitch.getName())
                    .icon(eachSwitch.getIcon())
                    .userDevice(exUserDevice)
                    .room(existingRoom)
                    .category(existingCategory)
                    .build();
            userSwitchRepository.save(newUserSwitch);
        }

        List<Asensor> existingAsensor = asensorRepository.findByDeviceId(existingDevice.getId());
        for (Asensor eachAsensor : existingAsensor) {
            UserAsensor newUserAsensor = UserAsensor.builder()
                    .name(eachAsensor.getName())
                    .icon(eachAsensor.getIcon())
                    .unit(eachAsensor.getUnit())
                    .updatePeriod(5000)
                    .userDevice(exUserDevice)
                    .room(existingRoom)
                    .category(existingCategory)
                    .build();
            userAsensorRepository.save(newUserAsensor);
        }

        List<Dsensor> existingDsensor = dsensorRepository.findByDeviceId(existingDevice.getId());
        for (Dsensor eachDsensor : existingDsensor) {
            UserDsensor newUserDsensor = UserDsensor.builder()
                    .name(eachDsensor.getName())
                    .icon(eachDsensor.getIcon())
                    .userDevice(exUserDevice)
                    .room(existingRoom)
                    .category(existingCategory)
                    .build();
            userDsensorRepository.save(newUserDsensor);
        }

        return exUserDevice;
    }
}
