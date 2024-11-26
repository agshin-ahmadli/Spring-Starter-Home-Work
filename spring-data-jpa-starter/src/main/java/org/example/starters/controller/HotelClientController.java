package org.example.starters.controller;

import lombok.RequiredArgsConstructor;
import org.example.starters.models.Client;
import org.example.starters.models.Room;
import org.example.starters.models.Ticket;
import org.example.starters.repositories.ClientRepository;
import org.example.starters.request.BookingRequest;
import org.example.starters.services.HotelServices;
import org.springframework.web.bind.annotation.*;
import response.TicketResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class HotelClientController {

    private final HotelServices hotelServices;


    @PostMapping("/register")
    public void registerClient(@RequestBody Client client) {
        hotelServices.registerClient(client);
    }


    @PostMapping("/ticket")
    TicketResponse createBooking(@RequestBody BookingRequest bookingRequest) {
        Room room = bookingRequest.getRoom();
        Client client = bookingRequest.getClient();
        int stayLength = bookingRequest.getStayLength();
        return hotelServices.registerTicket(room, client, stayLength);
    }
}
