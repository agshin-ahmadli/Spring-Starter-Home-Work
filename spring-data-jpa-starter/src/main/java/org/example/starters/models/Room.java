package org.example.starters.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private double roomPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Hotel hotel;

    @ManyToMany
    private List<Ticket> tickets;
}
