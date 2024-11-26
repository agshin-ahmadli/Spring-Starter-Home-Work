package org.example.starters.controller;
import common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.example.starters.models.Room;
import org.example.starters.services.*;
import org.springframework.web.bind.annotation.*;
import response.RoomResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel/")
public class HotelRoomsController {

    private final HotelServices hotelServices;


    @PostMapping(path = "/insertion")
    public void addRooms(@RequestBody Room room) {
        hotelServices.saveRoom(room);
    }

    @GetMapping("/get-room/{id}")
    public Room getRoom(@PathVariable Long id) {
        return hotelServices.getRoom(id);
    }

    @PutMapping("/update/room/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return hotelServices.updateRoomPrice(id, room.getRoomPrice());
    }

    @DeleteMapping("delete/room/{id}")
    public void deleteRoom(@PathVariable Long id) {
        hotelServices.deleteRoomById(id);
    }

    @GetMapping("/find/rooms")
public PageResponse<RoomResponse> findAllAvailableRooms(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return hotelServices.findAllRooms(page, size);
    }
}
