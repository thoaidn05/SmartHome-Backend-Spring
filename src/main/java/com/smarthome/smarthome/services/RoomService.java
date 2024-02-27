package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.RoomDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.helpers.FileHelper;
import com.smarthome.smarthome.models.Room;
import com.smarthome.smarthome.models.User;
import com.smarthome.smarthome.repositories.RoomRepository;
import com.smarthome.smarthome.repositories.UserRepository;
import com.smarthome.smarthome.services.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {
    @Value("${file_storage.room_image}")
    private String uploadFolder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Room createRoom(RoomDTO roomDTO) throws Exception {
        User existingUser = userRepository.findById(roomDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("This user is not existing"));
        Room newRoom = Room.builder()
                .name(roomDTO.getName())
                .image(FileHelper.storeImage(roomDTO.getImage(), uploadFolder))
                .user(existingUser)
                .build();
        return roomRepository.save(newRoom);
    }

    @Override
    public List<Room> getAllRoom(int userId) throws Exception {
        if (!userRepository.existsById(userId))
            throw new DataNotFoundException("User is not existing");
        return roomRepository.findByUserId(userId);
    }
}
