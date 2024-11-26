package org.example.starters.mapper;

import org.example.starters.models.Room;
import org.springframework.stereotype.Service;
import response.RoomResponse;

@Service
public class RoomMapper {

    public RoomResponse toRoomResponse(Room room) {
      return RoomResponse.builder()
              .status(room.getStatus())
              .price(room.getRoomPrice())
              .build();
    }
}
