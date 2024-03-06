package com.github.dtitar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class IteratingCollectionChallengeTest {
    RoomService service;
    Room[] rooms;

    @BeforeEach
    void setUp() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room manchester = new Room("Manshester", "Suite", 5, 250);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room oxford = new Room("Oxford", "Suite", 5, 225);
        Room victoria = new Room("Victoria", "Suite", 5, 225);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);

        this.rooms = new Room[]{cambridge, manchester, piccadilly, oxford, victoria, westminster};
        this.service = new RoomService();
        this.service.createRooms(rooms);
    }


    @Test
    void testApplyDiscountForEach() {
        Collection<Room> expectedRooms = service.getInventory()
                .stream()
                .peek(room -> room.setRate(room.getRate() - 10))
                .toList();
        service.applyDiscountForEach(10);
        Assertions.assertEquals(expectedRooms, service.getInventory()
                .stream()
                .toList(), "Collections are not equal\n");
    }

    @Test
    void testApplyDiscountStream() {
        Collection<Room> expectedRooms = service.getInventory()
                .stream()
                .peek(room -> room.setRate(room.getRate() - 10))
                .toList();
        service.applyDiscountStream(10);
        Assertions.assertEquals(expectedRooms, service.getInventory()
                .stream()
                .toList(), "Collections are not equal\n");
    }

    @Test
    void testGetRoomsByCapacityForEach() {
        Collection<Room> expectedRooms = service.getInventory();
        expectedRooms.removeIf(room -> room.getCapacity() < 5);
        Collection<Room> roomsWithCapacity = service.getRoomsByCapacityForEach(5);
        Assertions.assertEquals(expectedRooms, roomsWithCapacity);
    }

    @Test
    void testGetRoomsByCapacityStream() {
        Collection<Room> expectedRooms = service.getInventory();
        expectedRooms.removeIf(room -> room.getCapacity() < 4);
        Collection<Room> roomsWithCapacity = service.getRoomsByCapacityStream(4);
        Assertions.assertEquals(expectedRooms, roomsWithCapacity);
    }

    @Test
    void testGetRoomsByRateAndTypeForEach() {
        Collection<Room> expectedRooms = service.getInventory();
        expectedRooms.removeIf(room -> room.getRate() >= 200);
        expectedRooms.removeIf(room -> !room.getType()
                .equals("Premiere Room"));
        Collection<Room> roomsByRateAndType = service.getRoomByRateAndTypeForEach(200, "Premiere Room");
        Assertions.assertEquals(expectedRooms, roomsByRateAndType);
    }

    @Test
    void testGetRoomsByRateAndTypeStream() {
        Collection<Room> expectedRooms = service.getInventory();
        expectedRooms.removeIf(room -> room.getRate() >= 200);
        expectedRooms.removeIf(room -> !room.getType()
                .equals("Premiere Room"));
        Collection<Room> roomsWithCapacity = service.getRoomByRateAndTypeStream(200, "Premiere Room");
        Assertions.assertEquals(expectedRooms, roomsWithCapacity);
    }

}
