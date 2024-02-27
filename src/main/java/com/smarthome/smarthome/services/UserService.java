package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.UserDTO;
import com.smarthome.smarthome.dtos.UserLoginDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Role;
import com.smarthome.smarthome.models.User;
import com.smarthome.smarthome.repositories.UserRepository;
import com.smarthome.smarthome.services.interfaces.IUserService;
import com.smarthome.smarthome.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String email = userDTO.getEmail();
        if(userRepository.existsByEmail(email))
            throw new DataIntegrityViolationException("This email already exists");

        User newUser = User.builder()
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .address(userDTO.getAddress())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .fullName(userDTO.getFullName())
                .build();
        Role role = new Role();
        role.setId(2);
        newUser.setRole(role);
        return userRepository.save(newUser);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDTO.getEmail());
        if(optionalUser.isEmpty())
            throw new DataNotFoundException("Invalid email or password");
        User existingUser = optionalUser.get();
        if(!passwordEncoder.matches(userLoginDTO.getPassword(), existingUser.getPassword()))
            throw new DataNotFoundException("Invalid email or password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(), userLoginDTO.getPassword(),
                existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}
