package org.example.starters.mapper;

import org.example.starters.models.Room;
import org.example.starters.models.Ticket;
import org.springframework.stereotype.Service;
import response.TicketResponse;
@Service


public class TicketMapper {

    public TicketResponse toTicketResponse(Ticket ticket) {
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setClientId(ticket.getId());
        ticketResponse.setCheckInDate(ticket.getCheckIn());
        ticketResponse.setCheckOutDate(ticket.getCheckOut());
        ticketResponse.setRooms(ticket.getRooms());

        double price = 0;
        for (Room room : ticket.getRooms()) {
            price = room.getRoomPrice();
        }
        ticketResponse.setTotalPrice(price);
        return ticketResponse;
    }
}
