package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.DeviceDTO;
import com.smarthome.smarthome.helpers.FileHelper;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.services.interfaces.IDeviceService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/device")
public class DeviceController {
    @Value("${file_storage.device_image}")
    private String rootPath;
    @Autowired
    private IDeviceService iDeviceService;

    @PostMapping("/create")
    public ResponseEntity<?> createDevice(@Valid @ModelAttribute DeviceDTO deviceDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Device device = iDeviceService.createDevice(deviceDTO);
            return ResponseEntity.ok(device);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable int id, @Valid @ModelAttribute DeviceDTO deviceDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Device device = iDeviceService.updateDevice(id, deviceDTO);
            return ResponseEntity.ok(device);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDevice() {
        List<Device> deviceList = iDeviceService.getAllDevice();
        return ResponseEntity.ok(deviceList);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable("fileName") String fileName) {
        try {
            Path root = Paths.get(rootPath);
            Resource resource = FileHelper.loadFile(root, fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
