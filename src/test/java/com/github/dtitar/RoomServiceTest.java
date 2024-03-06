package com.github.dtitar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    RoomService service;

    Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
    Room manchester = new Room("Manshester", "Suite", 5, 250);
    Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
    Room oxford = new Room("Oxford", "Suite", 5, 225);
    Room victoria = new Room("Victoria", "Suite", 5, 225);
    Room westminster = new Room("Westminster", "Premiere Room", 4, 200);

    @BeforeEach
    void setUp() {
        this.service = new RoomService();
        this.service.createRoom("Piccadilly", "Guest Room", 3, 125);
        this.service.createRoom("Cambridge", "Premiere Room", 3, 175);
        this.service.createRoom("Victoria", "Suite", 5, 225);
    }

    @Test
    void testCreateRoom() {
        this.service.createRoom("Westminster", "Premiere Room", 4, 200);
        assertEquals(4, this.service.getInventory()
                .size());
    }

    @Test
    void testCreateRooms() {
        Room[] newRooms = {this.westminster, this.oxford, this.manchester};
        this.service.createRooms(newRooms);
        assertEquals(6, this.service.getInventory()
                .size());
    }

    @Test
    void testRemoveRoom() {
        this.service.removeRoom(new Room("Victoria", "Suite", 5, 225));
        assertEquals(2, this.service.getInventory()
                .size());
        assertFalse(this.service.getInventory()
                .contains(victoria));
    }

    @Test
    void testGetInventory() {
        assertEquals(3, this.service.getInventory()
                .size());
    }

    @Test
    void testAsArray() {
        Room[] rooms = this.service.asArray();
        assertEquals(3, rooms.length);
        assertEquals(this.piccadilly, rooms[0]);
        assertEquals(this.cambridge, rooms[1]);
        assertEquals(this.victoria, rooms[2]);
    }

    void testGetByType() {
        Collection<Room> guestRooms = this.service.getByType("Premiere Room");
        assertEquals(2, guestRooms.size());
        assertTrue(guestRooms.stream()
                .allMatch(r -> r.getType()
                        .equals("Premiere Room")));
        assertEquals(4, this.service.getInventory().size());
    }

    @AfterEach
    void tearDown() {
    }
}