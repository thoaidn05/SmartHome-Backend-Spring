package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.AsensorDTO;
import com.smarthome.smarthome.models.Asensor;
import com.smarthome.smarthome.services.interfaces.IAsensorService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/device/asensor")
public class AsensorController {
    @Autowired
    private IAsensorService iAsensorService;
    @PostMapping("/create")
    public ResponseEntity<?> createAsensor(@Valid @ModelAttribute AsensorDTO asensorDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Asensor newAsensor = iAsensorService.createAsensor(asensorDTO);
            return ResponseEntity.ok(newAsensor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAsensor(@PathVariable("id") int id ,@Valid @ModelAttribute AsensorDTO asensorDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Asensor updatedAsensor = iAsensorService.updateAsensor(id, asensorDTO);
            return ResponseEntity.ok(updatedAsensor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllAsensor() {
        List<Asensor> asensorList = iAsensorService.getAllAsensor();
        return ResponseEntity.ok(asensorList);
    }

}
