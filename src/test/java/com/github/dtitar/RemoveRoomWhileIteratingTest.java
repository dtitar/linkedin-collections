package com.github.dtitar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RemoveRoomWhileIteratingTest {

    @Test
    void roomRemovingUsingAdditionalCollection() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);
        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));
        Collection<Room> removeRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.isPetFriendly()) {
                removeRooms.add(room);
            }
        }
        rooms.removeAll(removeRooms);
        Assertions.assertEquals(1, rooms.size());
    }

    @Test
    void roomRemovingUsingIterator() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.isPetFriendly()) {
                iterator.remove();
            }
        }
        Assertions.assertEquals(1, rooms.size());
    }

    @Test
    void roomRemovingUsingCollectionRemoveIfMethod() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));
        rooms.removeIf(Room::isPetFriendly);
        Assertions.assertEquals(1, rooms.size());
    }

    @Test
    void roomRemovingUsingAnonymousClasses() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));
        rooms.stream()
                .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) {
                        System.out.format("Testing %s with result %b%n", room.getName(), room.isPetFriendly());
                        return room.isPetFriendly();
                    }
                })
                .forEach(new Consumer<Room>() {
                    @Override
                    public void accept(Room room) {
                        System.out.println(room.getName());
                    }
                });
    }

    @Test
    void roomRemovingUsingLambdas() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));
        rooms.stream()
                .filter(Room::isPetFriendly)
                .forEach(room -> System.out.println(room.getName()));
    }
}