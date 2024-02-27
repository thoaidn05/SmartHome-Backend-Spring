package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.UserDeviceDTO;
import com.smarthome.smarthome.models.UserDevice;
import com.smarthome.smarthome.services.interfaces.IUserDeviceService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user-device")
public class UserDeviceController {
    @Autowired
    private IUserDeviceService iUserDeviceService;
    @PostMapping("/create")
    public ResponseEntity<?> addUserDevice(@Valid @ModelAttribute UserDeviceDTO userDeviceDTO, BindingResult result) {
        try {
            if(result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            UserDevice userDevice = iUserDeviceService.addUserDevice(userDeviceDTO);
            return ResponseEntity.ok(userDevice);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
