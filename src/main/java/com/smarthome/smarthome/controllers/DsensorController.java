package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.DsensorDTO;
import com.smarthome.smarthome.models.Dsensor;
import com.smarthome.smarthome.services.interfaces.IDsensorService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/device/dsensor")
public class DsensorController {
    @Autowired
    private IDsensorService iDsensorService;
    @PostMapping("/create")
    public ResponseEntity<?> createDsensor(@Valid @ModelAttribute DsensorDTO dsensorDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Dsensor newDsensor = iDsensorService.createDsensor(dsensorDTO);
            return ResponseEntity.ok(newDsensor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDsensor(@PathVariable("id") int id ,@Valid @ModelAttribute DsensorDTO dsensorDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Dsensor updatedDsensor = iDsensorService.updateDsensor(id, dsensorDTO);
            return ResponseEntity.ok(updatedDsensor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllDsensor() {
        List<Dsensor> dsensorList = iDsensorService.getAllDsensor();
        return ResponseEntity.ok(dsensorList);
    }
}
