import java.util.ArrayList;
import java.util.List;

class Room {
    private int id;
    private String category;
    private double price;
    private boolean available;

    public Room(int id, String category, double price, boolean available) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public int getId() { return id; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

class Reservation {
    private String guestName;
    private Room room;
    private String date;

    public Reservation(String guestName, Room room, String date) {
        this.guestName = guestName;
        this.room = room;
        this.date = date;
    }

    public String getDetails() {
        return "Reservation for " + guestName + " in " + room.getCategory() + " room on " + date;
    }
}

class HotelReservationSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public HotelReservationSystem() {
        rooms.add(new Room(1, "Deluxe", 100.0, true));
        rooms.add(new Room(2, "Standard", 80.0, true));
        rooms.add(new Room(3, "Suite", 150.0, true));
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public String reserveRoom(String guestName, int roomId, String date) {
        for (Room room : rooms) {
            if (room.getId() == roomId && room.isAvailable()) {
                room.setAvailable(false);
                reservations.add(new Reservation(guestName, room, date));
                return "Reservation successful!";
            }
        }
        return "Room not available!";
    }
}

public class Main {
    public static void main(String[] args) {
        HotelReservationSystem hotel = new HotelReservationSystem();
        System.out.println("Available Rooms:");
        for (Room room : hotel.getAvailableRooms()) {
            System.out.println(room.getId() + " - " + room.getCategory() + " ($" + room.getPrice() + ")");
        }
        
        System.out.println(hotel.reserveRoom("John Doe", 1, "2025-03-01"));
    }
}

OUTPUT:

Available Rooms:
1 - Deluxe ($100.0)
2 - Standard ($80.0)
3 - Suite ($150.0)
Reservation successful!


