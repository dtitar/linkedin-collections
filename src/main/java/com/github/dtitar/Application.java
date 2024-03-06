package com.github.dtitar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Application {
    public static void main(String[] args) {
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
       rooms.forEach(room -> System.out.println(room.getName()));
    }
}
