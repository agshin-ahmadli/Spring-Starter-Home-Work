package org.example.starters.request;

import lombok.Getter;
import lombok.Setter;
import org.example.starters.models.Client;
import org.example.starters.models.Room;


@Setter
@Getter
public class BookingRequest {
    private Room room;
    private Client client;
    private int stayLength;
}
