package response;

import lombok.Getter;
import lombok.Setter;
import org.example.starters.models.Room;
import org.example.starters.models.Ticket;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TicketResponse {

    private Long clientId;
    private LocalDate checkInDate, checkOutDate;
    List<Room> rooms;
    double totalPrice;
}
