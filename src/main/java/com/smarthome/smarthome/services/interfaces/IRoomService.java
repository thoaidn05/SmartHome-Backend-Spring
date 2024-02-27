package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.RoomDTO;
import com.smarthome.smarthome.models.Category;
import com.smarthome.smarthome.models.Room;

import java.util.List;

public interface IRoomService {
    Room createRoom(RoomDTO roomDTO) throws Exception;
    List<Room> getAllRoom(int userId) throws Exception;

}
