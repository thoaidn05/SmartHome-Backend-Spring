package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.SwitchDTO;
import com.smarthome.smarthome.models.Switch;
import com.smarthome.smarthome.services.interfaces.ISwitchService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/device/switch")
public class SwitchController {
    @Autowired
    private ISwitchService iSwitchService;
    @PostMapping("/create")
    public ResponseEntity<?> createSwitch(@Valid @ModelAttribute SwitchDTO switchDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Switch newSwitch = iSwitchService.createSwitch(switchDTO);
            return ResponseEntity.ok(newSwitch);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateSwitch(@PathVariable("id") int id ,@Valid @ModelAttribute SwitchDTO switchDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Switch updatedSwitch = iSwitchService.updateSwitch(id, switchDTO);
            return ResponseEntity.ok(updatedSwitch);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllSwitch() {
        List<Switch> switchList = iSwitchService.getAllSwitch();
        return ResponseEntity.ok(switchList);
    }

}
