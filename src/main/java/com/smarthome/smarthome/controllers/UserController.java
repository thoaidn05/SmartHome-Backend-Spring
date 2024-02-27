package com.smarthome.smarthome.controllers;


import com.smarthome.smarthome.dtos.UserDTO;
import com.smarthome.smarthome.dtos.UserLoginDTO;
import com.smarthome.smarthome.models.User;
import com.smarthome.smarthome.services.interfaces.IUserService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            User user = iUserService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @ModelAttribute UserLoginDTO userLoginDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            String token = iUserService.login(userLoginDTO);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
