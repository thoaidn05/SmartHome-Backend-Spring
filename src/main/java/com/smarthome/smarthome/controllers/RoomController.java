package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.RoomDTO;
import com.smarthome.smarthome.models.Room;
import com.smarthome.smarthome.services.interfaces.IRoomService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/room")
public class RoomController {
    @Autowired
    private IRoomService iRoomService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@Valid @ModelAttribute RoomDTO roomDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Room room = iRoomService.createRoom(roomDTO);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllCategory(@PathVariable("userId") int userId) {
        try {
            List<Room> roomList = iRoomService.getAllRoom(userId);
            return ResponseEntity.ok(roomList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
