package org.example.starters.services;

import common.PageResponse;
import org.example.starters.models.Client;
import org.example.starters.models.Room;
import org.example.starters.models.Ticket;
import response.RoomResponse;
import response.TicketResponse;

public interface HotelServices {

    void saveRoom(Room room);

    Room getRoom(Long id);

    Room updateRoomPrice(Long id, double price);

    void deleteRoomById(Long id);

    PageResponse<RoomResponse> findAllRooms(int page, int pageSize);

    void registerClient(Client client);

    TicketResponse registerTicket(Room room, Client client, int stayLength);
}
