package org.example.starters.repositories;

import org.example.starters.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("""
            SELECT room
            FROM Room room
            WHERE room.status = 'AVAILABLE'""")
    Page<Room> findAllByAvailableRooms(Pageable pageable);


    @Query("""
            SELECT room
            FROM Room room
            WHERE room.status = 'AVAILABLE'
            AND room.Id = :roomId""")
    Optional<Room> findByRoomIdAndStatus(Long roomId);
}
