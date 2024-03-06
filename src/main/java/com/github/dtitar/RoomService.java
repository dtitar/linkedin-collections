package com.github.dtitar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {

    private Collection<Room> inventory;

    public RoomService() {
        this.inventory = new LinkedHashSet<>();
    }

    public void applyDiscountForEach(final double discount) {
        //Reduces the rate of each room by the provided discount
        this.getInventory()
                .forEach(room -> room.setRate(room.getRate() * (1 - discount)));
    }

    public void applyDiscountStream(final double discount) {
        //Reduces the rate of each room by the provided discount
        inventory.stream()
                .forEach(e -> e.setRate(e.getRate() * (1 - discount)));
    }

    public Collection<Room> getRoomsByCapacityForEach(final int requiredCapacity) {
        //Returns a new collection of rooms that meet or exceed the provided capacity
        Collection<Room> matches = new HashSet<>();
        for (Room room : this.getInventory()) {
            if (room.getCapacity() >= requiredCapacity) {
                matches.add(room);
            }
        }
        return matches;
    }

    public Collection<Room> getRoomsByCapacityStream(final int requiredCapacity) {
        //Returns a new collection of rooms that meet or exceed the provided capacity
        return this.getInventory()
                .stream()
                .filter(room -> room.getCapacity() >= requiredCapacity)
                .collect(Collectors.toSet());
    }

    public Collection<Room> getRoomByRateAndTypeForEach(final double rate, final String type) {
        /* Returns a new collection of rooms with a rate below
         * the provided rate and that match the provided type
         */
        Collection<Room> copy = new LinkedHashSet<>();
        for (Room room : this.getInventory()) {
            if (room.getRate() < rate && room.getType()
                    .equals(type))
                copy.add(room);
        }
        return copy;
    }

    public Collection<Room> getRoomByRateAndTypeStream(final double rate, final String type) {
        /* Returns a new collection of rooms with a rate below
         * the provided rate and that match the provided type
         */
        return this.getInventory()
                .stream()
                .filter(room -> room.getRate() < rate)
                .filter(room -> room.getType()
                        .equals(type))
                .collect(Collectors.toSet());
    }

    public Collection<Room> getInventory() {
        return inventory;
    }

    public void createRoom(String name, String type, int capacity, double rate) {
        this.inventory.add(new Room(name, type, capacity, rate));
    }

    public void createRooms(Room[] rooms) {
        this.inventory.addAll(List.of(rooms));
    }

    public void removeRoom(Room room) {
        this.inventory.remove(room);
    }

    public boolean hasRoom(Room room) {
        return this.inventory.contains(room);
    }

    public Room[] asArray() {
        return this.inventory.toArray(new Room[0]);
    }

    public Collection<Room> getByType(String type) {
        Collection<Room> copy = new HashSet(this.inventory);
        copy.removeIf(room -> !room.getType()
                .equals(type));
        return copy;
    }


}
