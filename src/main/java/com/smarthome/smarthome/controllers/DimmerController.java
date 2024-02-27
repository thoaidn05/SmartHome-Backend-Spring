package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.DimmerDTO;
import com.smarthome.smarthome.models.Dimmer;
import com.smarthome.smarthome.services.interfaces.IDimmerService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/device/dimmer")
public class DimmerController {
    @Autowired
    private IDimmerService iDimmerService;
    @PostMapping("/create")
    public ResponseEntity<?> createDimmer(@Valid @ModelAttribute DimmerDTO dimmerDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Dimmer dimmer = iDimmerService.createDimmer(dimmerDTO);
            return ResponseEntity.ok(dimmer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDimmer(@PathVariable("id") int id ,@Valid @ModelAttribute DimmerDTO dimmerDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Dimmer dimmer = iDimmerService.updateDimmer(id, dimmerDTO);
            return ResponseEntity.ok(dimmer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllDevice() {
        List<Dimmer> dimmerList = iDimmerService.getAllDimmer();
        return ResponseEntity.ok(dimmerList);
    }
}
