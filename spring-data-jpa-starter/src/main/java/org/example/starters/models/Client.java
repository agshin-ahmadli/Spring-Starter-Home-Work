package org.example.starters.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> roomList;

    @ManyToOne
    private Hotel hotel;

    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> bookingTicket = new ArrayList<>();

}
