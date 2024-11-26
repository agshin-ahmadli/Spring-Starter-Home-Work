package response;

import lombok.*;
import org.example.starters.models.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {

    Status status;
    double price;
}
