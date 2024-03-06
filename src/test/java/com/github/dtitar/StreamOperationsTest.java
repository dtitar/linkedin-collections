package com.github.dtitar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamOperationsTest {

    @Test
    void streamOperations() {
        Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175);
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125);
        Room westminster = new Room("Westminster", "Premiere Room", 4, 200);
        cambridge.setPetFriendly(true);
        piccadilly.setPetFriendly(true);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(piccadilly, cambridge, westminster));

        Collection<Room> petFriendlyRooms = rooms.stream().filter(Room::isPetFriendly).toList();
        petFriendlyRooms.stream()
                .map(Room::getName)
                .forEach(System.out::println);

        double total = petFriendlyRooms.stream()
                .mapToDouble(Room::getRate)
                .sum();
        System.out.println(total);
    }
}