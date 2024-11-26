package org.example.starters.services.impl;

import common.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.starters.handler.RoomNotFoundException;
import org.example.starters.mapper.TicketMapper;
import org.example.starters.models.Client;
import org.example.starters.models.Room;
import org.example.starters.models.Status;
import org.example.starters.models.Ticket;
import org.example.starters.repositories.ClientRepository;
import org.example.starters.repositories.RoomRepository;
import org.example.starters.repositories.TicketRepository;
import org.example.starters.services.HotelServices;
import org.example.starters.mapper.RoomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import response.RoomResponse;
import response.TicketResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class HotelServicesImpl implements HotelServices {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final ClientRepository clientRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;


    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }


    @Override
    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElseThrow(()-> new RoomNotFoundException(" room not found."));
    }


    @Override
    public Room updateRoomPrice(Long id, double price) {
        Room room = roomRepository.findById(id).orElseThrow(()-> new RoomNotFoundException(" room not found."));

        room.setRoomPrice(price);
        return roomRepository.save(room);
    }


    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }


    @Override
    public PageResponse<RoomResponse> findAllRooms(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("roomPrice").ascending());
        Page<Room> roomPage = roomRepository.findAllByAvailableRooms(pageable);
        List<RoomResponse> roomResponses = roomPage.stream()
                .map(roomMapper::toRoomResponse).toList();

        return new PageResponse<>(
                roomResponses
                , roomPage.getNumber()
                ,roomPage.getSize()
                ,roomPage.getTotalElements()
                ,roomPage.getTotalPages()
                ,roomPage.isLast()
                ,roomPage.isFirst()
        );
    }


    @Override
    public void registerClient(Client client) {
        clientRepository.save(client);
    }


    @Override
    @Transactional
    public TicketResponse registerTicket(Room room, Client client, int stayLength) {
        Room roomVerified = roomRepository.findByRoomIdAndStatus(room.getId())
                .orElseThrow(()-> new RoomNotFoundException(" room not found."));

        Ticket ticket = new Ticket();
        ticket.setCheckIn(LocalDate.now());
        ticket.setCheckOut(LocalDate.now().plusDays(stayLength));
        ticket.setClient(client);
        ticket.setPrice(room.getRoomPrice());
        ticket.setRooms(client.getRoomList());
        Ticket ticket1 = ticketRepository.save(ticket);
        List<Ticket>tickets = new ArrayList<>();
        tickets.add(ticket1);

        roomVerified.setStatus(Status.RESERVED);
        roomVerified.setClient(client);
        roomVerified.setTickets(tickets);
        roomRepository.save(roomVerified);

        return ticketMapper.toTicketResponse(ticket1);
    }
}
