package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByIdAndUserId(int roomId, int userId);
    List<Room> findByUserId(int userId);
}
