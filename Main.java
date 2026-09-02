import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


// User and authentication classes
class User {
    private String userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Booking> bookings;
    
    public User(String userId, String username, String password, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    public void displayInfo() {
        System.out.println("\n===== USER PROFILE =====");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("=======================");
    }
}


// Transportation classes
abstract class Transport {
    private String id;
    private String name;
    private String source;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private double price;
    private int totalSeats;
    private int availableSeats;
    
    public Transport(String id, String name, String source, String destination, 
                    String departureDate, String departureTime, String arrivalDate, 
                    String arrivalTime, double price, int totalSeats) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // Initially all seats are available
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSource() {
        return source;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public String getArrivalDate() {
        return arrivalDate;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getTotalSeats() {
        return totalSeats;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }
    
    public boolean bookSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }
    
    public void cancelBooking(int numberOfSeats) {
        availableSeats += numberOfSeats;
        if (availableSeats > totalSeats) {
            availableSeats = totalSeats;
        }
    }
    
    public abstract void displayDetails();
    
    public String getDuration() {
        // Simple duration calculation (for real application, use proper date-time calculations)
        return "Calculated duration";
    }
}


class Flight extends Transport {
    private String airline;
    private String flightNumber;
    private String aircraftType;
    private boolean hasMeal;
    
    public Flight(String id, String name, String source, String destination, 
                 String departureDate, String departureTime, String arrivalDate, 
                 String arrivalTime, double price, int totalSeats, 
                 String airline, String flightNumber, String aircraftType, boolean hasMeal) {
        super(id, name, source, destination, departureDate, departureTime, 
             arrivalDate, arrivalTime, price, totalSeats);
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.aircraftType = aircraftType;
        this.hasMeal = hasMeal;
    }
    
    public String getAirline() {
        return airline;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public String getAircraftType() {
        return aircraftType;
    }
    
    public boolean hasMeal() {
        return hasMeal;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n===== FLIGHT DETAILS =====");
        System.out.println("Flight ID: " + getId());
        System.out.println("Airline: " + airline);
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Route: " + getSource() + " to " + getDestination());
        System.out.println("Departure: " + getDepartureDate() + " " + getDepartureTime());
        System.out.println("Arrival: " + getArrivalDate() + " " + getArrivalTime());
        System.out.println("Duration: " + getDuration());
        System.out.println("Aircraft: " + aircraftType);
        System.out.println("Meal Service: " + (hasMeal ? "Available" : "Not Available"));
        System.out.printf("Price: ₹%.2f\n", getPrice());
        System.out.println("Available Seats: " + getAvailableSeats() + "/" + getTotalSeats());
        System.out.println("=========================");
    }
}


class Train extends Transport {
    private String trainNumber;
    private String trainType; // Express, Superfast, etc.
    private List<String> intermediateStops;
    private boolean hasSleeperClass;
    
    public Train(String id, String name, String source, String destination, 
                String departureDate, String departureTime, String arrivalDate, 
                String arrivalTime, double price, int totalSeats,
                String trainNumber, String trainType, boolean hasSleeperClass) {
        super(id, name, source, destination, departureDate, departureTime, 
             arrivalDate, arrivalTime, price, totalSeats);
        this.trainNumber = trainNumber;
        this.trainType = trainType;
        this.hasSleeperClass = hasSleeperClass;
        this.intermediateStops = new ArrayList<>();
    }
    
    public String getTrainNumber() {
        return trainNumber;
    }
    
    public String getTrainType() {
        return trainType;
    }
    
    public List<String> getIntermediateStops() {
        return intermediateStops;
    }
    
    public void addIntermediateStop(String stop) {
        intermediateStops.add(stop);
    }
    
    public boolean hasSleeperClass() {
        return hasSleeperClass;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n===== TRAIN DETAILS =====");
        System.out.println("Train ID: " + getId());
        System.out.println("Train Name: " + getName());
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Type: " + trainType);
        System.out.println("Route: " + getSource() + " to " + getDestination());
        System.out.println("Departure: " + getDepartureDate() + " " + getDepartureTime());
        System.out.println("Arrival: " + getArrivalDate() + " " + getArrivalTime());
        System.out.println("Duration: " + getDuration());
        System.out.println("Sleeper Class: " + (hasSleeperClass ? "Available" : "Not Available"));
        
        if (!intermediateStops.isEmpty()) {
            System.out.println("Intermediate Stops:");
            for (String stop : intermediateStops) {
                System.out.println("- " + stop);
            }
        }
        
        System.out.printf("Price: ₹%.2f\n", getPrice());
        System.out.println("Available Seats: " + getAvailableSeats() + "/" + getTotalSeats());
        System.out.println("========================");
    }
}


// Hotel classes
class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String address;
    private int starRating;
    private List<Room> rooms;
    private List<String> amenities;
    private double rating;
    
    public Hotel(String hotelId, String name, String location, String address, int starRating, double rating) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.address = address;
        this.starRating = starRating;
        this.rating = rating;
        this.rooms = new ArrayList<>();
        this.amenities = new ArrayList<>();
    }
    
    public String getHotelId() {
        return hotelId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getStarRating() {
        return starRating;
    }
    
    public double getRating() {
        return rating;
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    public List<String> getAmenities() {
        return amenities;
    }
    
    public void addAmenity(String amenity) {
        amenities.add(amenity);
    }
    
    public List<Room> getAvailableRooms(String checkInDate, String checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    
    public void displayDetails() {
        System.out.println("\n===== HOTEL DETAILS =====");
        System.out.println("Hotel ID: " + hotelId);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Address: " + address);
        System.out.println("Star Rating: " + starRating);
        System.out.println("Guest Rating: " + rating + "/5.0");
        
        System.out.println("\nAmenities:");
        for (String amenity : amenities) {
            System.out.println("- " + amenity);
        }
        
        System.out.println("\nAvailable Room Types:");
        Map<String, Double> roomTypePrices = new HashMap<>();
        for (Room room : rooms) {
            roomTypePrices.put(room.getType(), room.getPrice());
        }
        
        for (Map.Entry<String, Double> entry : roomTypePrices.entrySet()) {
            System.out.printf("- %s: ₹%.2f per night\n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("=========================");
    }
}


class Room {
    private String roomId;
    private String type; // Single, Double, Suite, etc.
    private double price;
    private boolean available;
    private Map<String, Boolean> bookingDates; // Map of dates to availability
    
    public Room(String roomId, String type, double price) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.available = true;
        this.bookingDates = new HashMap<>();
    }
    
    public String getRoomId() {
        return roomId;
    }
    
    public String getType() {
        return type;
    }
    
    public double getPrice() {
        return price;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public boolean isAvailable(String checkInDate, String checkOutDate) {
        // For simplicity, we just check if the room is marked as available
        // In a real app, we would check date ranges
        return available;
    }
    
    public void book(String checkInDate, String checkOutDate) {
        // In a real app, we would mark specific dates as booked
        available = false;
    }
    
    public void cancelBooking(String checkInDate, String checkOutDate) {
        // In a real app, we would clear bookings for specific dates
        available = true;
    }
}


// Booking classes
class Booking {
    private String bookingId;
    private User user;
    private Date bookingDate;
    private BookingType type;
    private double totalPrice;
    private String status; // Confirmed, Cancelled, Completed
    private Payment payment;
    
    public Booking(String bookingId, User user, BookingType type) {
        this.bookingId = bookingId;
        this.user = user;
        this.bookingDate = new Date();
        this.type = type;
        this.totalPrice = 0.0; // Will be set later
        this.status = "Pending";
        this.payment = null;
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public User getUser() {
        return user;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }
    
    public BookingType getType() {
        return type;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Payment getPayment() {
        return payment;
    }
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public void displayBookingDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        System.out.println("\n===== BOOKING DETAILS =====");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("User: " + user.getName());
        System.out.println("Booking Date: " + dateFormat.format(bookingDate));
        System.out.println("Booking Type: " + type.getTypeName());
        System.out.printf("Total Price: ₹%.2f\n", totalPrice);
        System.out.println("Status: " + status);
        
        if (payment != null) {
            System.out.println("\nPayment Details:");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Payment Date: " + dateFormat.format(payment.getPaymentDate()));
            System.out.println("Payment Method: " + payment.getPaymentMethod());
            System.out.printf("Amount: ₹%.2f\n", payment.getAmount());
            System.out.println("Status: " + payment.getStatus());
        }
        
        type.displayDetails();
        System.out.println("\n============================");
    }
}


abstract class BookingType {
    private String bookingTypeId;
    
    public BookingType(String bookingTypeId) {
        this.bookingTypeId = bookingTypeId;
    }
    
    public String getBookingTypeId() {
        return bookingTypeId;
    }
    
    public abstract String getTypeName();
    public abstract void displayDetails();
}


class FlightBooking extends BookingType {
    private Flight flight;
    private int numberOfPassengers;
    private List<String> passengerNames;
    private String seatClass; // Economy, Business, First
    private boolean checkedIn;
    
    public FlightBooking(String bookingTypeId, Flight flight, int numberOfPassengers, String seatClass) {
        super(bookingTypeId);
        this.flight = flight;
        this.numberOfPassengers = numberOfPassengers;
        this.seatClass = seatClass;
        this.passengerNames = new ArrayList<>();
        this.checkedIn = false;
        
        // Book the seats
        flight.bookSeats(numberOfPassengers);
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    
    public List<String> getPassengerNames() {
        return passengerNames;
    }
    
    public void addPassengerName(String name) {
        passengerNames.add(name);
    }
    
    public String getSeatClass() {
        return seatClass;
    }
    
    public boolean isCheckedIn() {
        return checkedIn;
    }
    
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    
    @Override
    public String getTypeName() {
        return "Flight";
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n----- Flight Details -----");
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Route: " + flight.getSource() + " to " + flight.getDestination());
        System.out.println("Departure: " + flight.getDepartureDate() + " " + flight.getDepartureTime());
        System.out.println("Arrival: " + flight.getArrivalDate() + " " + flight.getArrivalTime());
        System.out.println("Seat Class: " + seatClass);
        System.out.println("Number of Passengers: " + numberOfPassengers);
        
        if (!passengerNames.isEmpty()) {
            System.out.println("\nPassengers:");
            for (int i = 0; i < passengerNames.size(); i++) {
                System.out.println((i + 1) + ". " + passengerNames.get(i));
            }
        }
        
        System.out.println("Check-in Status: " + (checkedIn ? "Checked In" : "Not Checked In"));
    }
}


class TrainBooking extends BookingType {
    private Train train;
    private int numberOfPassengers;
    private List<String> passengerNames;
    private String seatClass; // General, Sleeper, AC, etc.
    
    public TrainBooking(String bookingTypeId, Train train, int numberOfPassengers, String seatClass) {
        super(bookingTypeId);
        this.train = train;
        this.numberOfPassengers = numberOfPassengers;
        this.seatClass = seatClass;
        this.passengerNames = new ArrayList<>();
        
        // Book the seats
        train.bookSeats(numberOfPassengers);
    }
    
    public Train getTrain() {
        return train;
    }
    
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    
    public List<String> getPassengerNames() {
        return passengerNames;
    }
    
    public void addPassengerName(String name) {
        passengerNames.add(name);
    }
    
    public String getSeatClass() {
        return seatClass;
    }
    
    @Override
    public String getTypeName() {
        return "Train";
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n----- Train Details -----");
        System.out.println("Train Name: " + train.getName());
        System.out.println("Train Number: " + train.getTrainNumber());
        System.out.println("Route: " + train.getSource() + " to " + train.getDestination());
        System.out.println("Departure: " + train.getDepartureDate() + " " + train.getDepartureTime());
        System.out.println("Arrival: " + train.getArrivalDate() + " " + train.getArrivalTime());
        System.out.println("Seat Class: " + seatClass);
        System.out.println("Number of Passengers: " + numberOfPassengers);
        
        if (!passengerNames.isEmpty()) {
            System.out.println("\nPassengers:");
            for (int i = 0; i < passengerNames.size(); i++) {
                System.out.println((i + 1) + ". " + passengerNames.get(i));
            }
        }
    }
}


class HotelBooking extends BookingType {
    private Hotel hotel;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfGuests;
    private int numberOfRooms;
    private List<String> guestNames;
    
    public HotelBooking(String bookingTypeId, Hotel hotel, Room room, 
                       String checkInDate, String checkOutDate, 
                       int numberOfGuests, int numberOfRooms) {
        super(bookingTypeId);
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.numberOfRooms = numberOfRooms;
        this.guestNames = new ArrayList<>();
        
        // Book the room
        room.book(checkInDate, checkOutDate);
    }
    
    public Hotel getHotel() {
        return hotel;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String getCheckInDate() {
        return checkInDate;
    }
    
    public String getCheckOutDate() {
        return checkOutDate;
    }
    
    public int getNumberOfGuests() {
        return numberOfGuests;
    }
    
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    
    public List<String> getGuestNames() {
        return guestNames;
    }
    
    public void addGuestName(String name) {
        guestNames.add(name);
    }
    
    @Override
    public String getTypeName() {
        return "Hotel";
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n----- Hotel Booking Details -----");
        System.out.println("Hotel: " + hotel.getName());
        System.out.println("Location: " + hotel.getLocation());
        System.out.println("Room Type: " + room.getType());
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Number of Guests: " + numberOfGuests);
        System.out.println("Number of Rooms: " + numberOfRooms);
        
        if (!guestNames.isEmpty()) {
            System.out.println("\nGuests:");
            for (int i = 0; i < guestNames.size(); i++) {
                System.out.println((i + 1) + ". " + guestNames.get(i));
            }
        }
        
        System.out.printf("Price per Night: ₹%.2f\n", room.getPrice());
    }
}


// Payment class
class Payment {
    private String paymentId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private String transactionId;
    
    public Payment(String paymentId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = new Date();
        this.paymentMethod = paymentMethod;
        this.status = "Pending";
        this.transactionId = generateTransactionId();
    }
    
    private String generateTransactionId() {
        // In real app, this would be more sophisticated
        return "TXN" + System.currentTimeMillis() % 10000;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public boolean processPayment() {
        // In a real app, this would integrate with a payment gateway
        // For demo purposes, we'll simulate a successful payment
        setStatus("Completed");
        return true;
    }
}


// Search and booking management classes
class SearchService {
    private List<Flight> flights;
    private List<Train> trains;
    private List<Hotel> hotels;
    
    public SearchService() {
        flights = new ArrayList<>();
        trains = new ArrayList<>();
        hotels = new ArrayList<>();
        
        // Initialize with sample data
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Sample Flights
        Flight f1 = new Flight("F1001", "Morning Express", "Mumbai", "Delhi", 
                              "15/08/2023", "08:00", "15/08/2023", "10:30", 5999.99, 180,
                              "Air India", "AI123", "Boeing 737", true);
        Flight f2 = new Flight("F1002", "Evening Express", "Mumbai", "Delhi", 
                              "15/08/2023", "18:00", "15/08/2023", "20:30", 6499.99, 180,
                              "IndiGo", "6E456", "Airbus A320", true);
        Flight f3 = new Flight("F1003", "Midnight Express", "Mumbai", "Bangalore", 
                              "16/08/2023", "00:15", "16/08/2023", "01:45", 4999.99, 150,
                              "SpiceJet", "SG789", "Boeing 737", false);
        Flight f4 = new Flight("F1004", "Morning Special", "Chennai", "Hyderabad", 
                              "16/08/2023", "10:00", "16/08/2023", "11:30", 3999.99, 120,
                              "Vistara", "UK234", "Boeing 737", true);
        Flight f5 = new Flight("F1005", "Afternoon Flight", "Delhi", "Mumbai", 
                              "17/08/2023", "14:00", "17/08/2023", "16:30", 6299.99, 200,
                              "AirAsia", "I5789", "Airbus A321", true);
        Flight f6 = new Flight("F1006", "Express Connect", "Hyderabad", "Bangalore", 
                              "15/08/2023", "07:15", "15/08/2023", "08:45", 3599.99, 150,
                              "IndiGo", "6E111", "Airbus A320", true);
        Flight f7 = new Flight("F1007", "Southern Express", "Chennai", "Visakhapatnam", 
                              "15/08/2023", "09:30", "15/08/2023", "11:00", 4299.99, 140,
                              "SpiceJet", "SG222", "Boeing 737", true);
        Flight f8 = new Flight("F1008", "Deccan Connect", "Hyderabad", "Vijayawada", 
                              "16/08/2023", "11:45", "16/08/2023", "12:45", 2899.99, 120,
                              "Air India", "AI333", "Airbus A319", false);
        Flight f9 = new Flight("F1009", "Coastal Flyer", "Visakhapatnam", "Chennai", 
                              "17/08/2023", "16:20", "17/08/2023", "17:50", 4199.99, 130,
                              "IndiGo", "6E444", "Airbus A320", true);
        Flight f10 = new Flight("F1010", "IT Express", "Bangalore", "Hyderabad", 
                               "15/08/2023", "19:00", "15/08/2023", "20:30", 3799.99, 160,
                               "Vistara", "UK555", "Boeing 737", true);
        
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);
        flights.add(f5);
        flights.add(f6);
        flights.add(f7);
        flights.add(f8);
        flights.add(f9);
        flights.add(f10);
        
        // Sample Trains
        Train t1 = new Train("T1001", "Rajdhani Express", "Mumbai", "Delhi", 
                            "15/08/2023", "16:30", "16/08/2023", "08:45", 2199.99, 400,
                            "12951", "Rajdhani", true);
        t1.addIntermediateStop("Surat");
        t1.addIntermediateStop("Vadodara");
        t1.addIntermediateStop("Kota");
        
        Train t2 = new Train("T1002", "Shatabdi Express", "Chennai", "Bangalore", 
                            "16/08/2023", "06:00", "16/08/2023", "11:00", 1499.99, 350,
                            "12007", "Shatabdi", false);
        t2.addIntermediateStop("Katpadi");
        t2.addIntermediateStop("Krishnarajapuram");
        
        Train t3 = new Train("T1003", "Duronto Express", "Kolkata", "Delhi", 
                            "17/08/2023", "08:15", "18/08/2023", "10:45", 2399.99, 300,
                            "12273", "Duronto", true);
        t3.addIntermediateStop("Asansol");
        t3.addIntermediateStop("Gaya");
        t3.addIntermediateStop("Kanpur");
        
        Train t4 = new Train("T1004", "Godavari Express", "Hyderabad", "Visakhapatnam", 
                            "15/08/2023", "20:15", "16/08/2023", "08:30", 1899.99, 320,
                            "12727", "Superfast", true);
        t4.addIntermediateStop("Warangal");
        t4.addIntermediateStop("Vijayawada");
        t4.addIntermediateStop("Rajahmundry");
        
        Train t5 = new Train("T1005", "Charminar Express", "Chennai", "Hyderabad", 
                            "16/08/2023", "14:30", "17/08/2023", "04:15", 1699.99, 280,
                            "12759", "Express", true);
        t5.addIntermediateStop("Gudur");
        t5.addIntermediateStop("Nellore");
        t5.addIntermediateStop("Ongole");
        t5.addIntermediateStop("Vijayawada");
        
        Train t6 = new Train("T1006", "Bangalore Express", "Vijayawada", "Bangalore", 
                            "15/08/2023", "22:45", "16/08/2023", "11:30", 1599.99, 300,
                            "16523", "Express", true);
        t6.addIntermediateStop("Guntur");
        t6.addIntermediateStop("Guntakal");
        t6.addIntermediateStop("Anantapur");
        
        trains.add(t1);
        trains.add(t2);
        trains.add(t3);
        trains.add(t4);
        trains.add(t5);
        trains.add(t6);
        
        // Sample Hotels
        Hotel h1 = new Hotel("H1001", "Taj Hotel", "Mumbai", "Apollo Bunder, Colaba, Mumbai, MH", 5, 4.7);
        h1.addAmenity("Free Wi-Fi");
        h1.addAmenity("Swimming Pool");
        h1.addAmenity("Fitness Center");
        h1.addAmenity("Restaurant");
        h1.addAmenity("Spa");
        
        Room r1h1 = new Room("R1001", "Single", 12999.99);
        Room r2h1 = new Room("R1002", "Double", 17999.99);
        Room r3h1 = new Room("R1003", "Suite", 29999.99);
        h1.addRoom(r1h1);
        h1.addRoom(r2h1);
        h1.addRoom(r3h1);
        
        Hotel h2 = new Hotel("H1002", "The Leela Palace", "Delhi", "Diplomatic Enclave, Chanakyapuri, New Delhi, DL", 5, 4.8);
        h2.addAmenity("Free Wi-Fi");
        h2.addAmenity("Swimming Pool");
        h2.addAmenity("Spa & Wellness Centre");
        h2.addAmenity("Multiple Restaurants");
        
        Room r1h2 = new Room("R2001", "Single", 10999.99);
        Room r2h2 = new Room("R2002", "Double", 15999.99);
        Room r3h2 = new Room("R2003", "Suite", 25999.99);
        h2.addRoom(r1h2);
        h2.addRoom(r2h2);
        h2.addRoom(r3h2);
        
        Hotel h3 = new Hotel("H1003", "ITC Grand Chola", "Chennai", "Mount Road, Little Mount, Chennai, TN", 5, 4.6);
        h3.addAmenity("Free Wi-Fi");
        h3.addAmenity("Fitness Center");
        h3.addAmenity("Spa");
        h3.addAmenity("Multiple Restaurants");
        
        Room r1h3 = new Room("R3001", "Single", 8999.99);
        Room r2h3 = new Room("R3002", "Double", 12999.99);
        h3.addRoom(r1h3);
        h3.addRoom(r2h3);
        
        Hotel h4 = new Hotel("H1004", "Taj Krishna", "Hyderabad", "Road No. 1, Banjara Hills, Hyderabad, TG", 5, 4.5);
        h4.addAmenity("Free Wi-Fi");
        h4.addAmenity("Swimming Pool");
        h4.addAmenity("Fitness Center");
        h4.addAmenity("Restaurant");
        h4.addAmenity("Business Center");
        
        Room r1h4 = new Room("R4001", "Single", 9499.99);
        Room r2h4 = new Room("R4002", "Double", 14999.99);
        Room r3h4 = new Room("R4003", "Suite", 24999.99);
        h4.addRoom(r1h4);
        h4.addRoom(r2h4);
        h4.addRoom(r3h4);
        
        Hotel h5 = new Hotel("H1005", "The Oberoi", "Bangalore", "MG Road, Bangalore, KA", 5, 4.7);
        h5.addAmenity("Free Wi-Fi");
        h5.addAmenity("Swimming Pool");
        h5.addAmenity("Spa & Wellness");
        h5.addAmenity("Fine Dining");
        h5.addAmenity("Airport Shuttle");
        
        Room r1h5 = new Room("R5001", "Single", 9999.99);
        Room r2h5 = new Room("R5002", "Double", 14499.99);
        Room r3h5 = new Room("R5003", "Suite", 22999.99);
        h5.addRoom(r1h5);
        h5.addRoom(r2h5);
        h5.addRoom(r3h5);
        
        Hotel h6 = new Hotel("H1006", "Novotel", "Visakhapatnam", "Beach Road, Visakhapatnam, AP", 4, 4.3);
        h6.addAmenity("Free Wi-Fi");
        h6.addAmenity("Swimming Pool");
        h6.addAmenity("Fitness Center");
        h6.addAmenity("Beachfront View");
        
        Room r1h6 = new Room("R6001", "Single", 6999.99);
        Room r2h6 = new Room("R6002", "Double", 9999.99);
        Room r3h6 = new Room("R6003", "Suite", 16999.99);
        h6.addRoom(r1h6);
        h6.addRoom(r2h6);
        h6.addRoom(r3h6);
        
        Hotel h7 = new Hotel("H1007", "Fortune Murali Park", "Vijayawada", "M.G. Road, Vijayawada, AP", 4, 4.1);
        h7.addAmenity("Free Wi-Fi");
        h7.addAmenity("Restaurant");
        h7.addAmenity("Business Center");
        h7.addAmenity("Fitness Center");
        
        Room r1h7 = new Room("R7001", "Single", 5999.99);
        Room r2h7 = new Room("R7002", "Double", 8499.99);
        Room r3h7 = new Room("R7003", "Suite", 13999.99);
        h7.addRoom(r1h7);
        h7.addRoom(r2h7);
        h7.addRoom(r3h7);
        
        hotels.add(h1);
        hotels.add(h2);
        hotels.add(h3);
        hotels.add(h4);
        hotels.add(h5);
        hotels.add(h6);
        hotels.add(h7);
    }
    
    public List<Flight> searchFlights(String source, String destination, String date) {
        List<Flight> results = new ArrayList<>();
        
        // If all search fields are empty, return all flights
        if (source.isEmpty() && destination.isEmpty() && date.isEmpty()) {
            return flights;
        }
        
        for (Flight flight : flights) {
            // More relaxed matching - partial match or empty criteria
            boolean sourceMatch = source.isEmpty() || 
                                  flight.getSource().toLowerCase().contains(source.toLowerCase());
            boolean destMatch = destination.isEmpty() || 
                                flight.getDestination().toLowerCase().contains(destination.toLowerCase());
            boolean dateMatch = date.isEmpty() || 
                               flight.getDepartureDate().equals(date);
            
            if (sourceMatch && destMatch && dateMatch) {
                results.add(flight);
            }
        }
        return results;
    }
    
    public Flight getFlightById(String flightId) {
        for (Flight flight : flights) {
            if (flight.getId().equals(flightId)) {
                return flight;
            }
        }
        return null;
    }
    
    public List<Train> searchTrains(String source, String destination, String date) {
        List<Train> results = new ArrayList<>();
        
        // If all search fields are empty, return all trains
        if (source.isEmpty() && destination.isEmpty() && date.isEmpty()) {
            return trains;
        }
        
        for (Train train : trains) {
            // More relaxed matching - partial match or empty criteria
            boolean sourceMatch = source.isEmpty() || 
                                  train.getSource().toLowerCase().contains(source.toLowerCase());
            boolean destMatch = destination.isEmpty() || 
                               train.getDestination().toLowerCase().contains(destination.toLowerCase());
            boolean dateMatch = date.isEmpty() || 
                               train.getDepartureDate().equals(date);
            
            if (sourceMatch && destMatch && dateMatch) {
                results.add(train);
            }
        }
        return results;
    }
    
    public Train getTrainById(String trainId) {
        for (Train train : trains) {
            if (train.getId().equals(trainId)) {
                return train;
            }
        }
        return null;
    }
    
    public List<Hotel> searchHotels(String location, String checkInDate, String checkOutDate) {
        List<Hotel> results = new ArrayList<>();
        
        // If location is empty, return all hotels
        if (location.isEmpty()) {
            return hotels;
        }
        
        for (Hotel hotel : hotels) {
            // More relaxed matching for location
            boolean locationMatch = hotel.getLocation().toLowerCase().contains(location.toLowerCase());
            
            if (locationMatch) {
                results.add(hotel);
            }
        }
        return results;
    }
    
    public Hotel getHotelById(String hotelId) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId().equals(hotelId)) {
                return hotel;
            }
        }
        return null;
    }
    
    public Room getRoomById(Hotel hotel, String roomId) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }
}


class BookingService {
    private List<Booking> bookings;
    private int bookingIdCounter;
    private int paymentIdCounter;
    
    public BookingService() {
        bookings = new ArrayList<>();
        bookingIdCounter = 1000;
        paymentIdCounter = 5000;
    }
    
    public Booking createFlightBooking(User user, Flight flight, int numberOfPassengers, String seatClass) {
        bookingIdCounter++;
        String bookingId = "B" + bookingIdCounter;
        
        FlightBooking flightBooking = new FlightBooking("FB" + bookingIdCounter, flight, numberOfPassengers, seatClass);
        Booking booking = new Booking(bookingId, user, flightBooking);
        
        // Calculate total price (potentially with class multipliers in a real app)
        double totalPrice = flight.getPrice() * numberOfPassengers;
        booking.setTotalPrice(totalPrice);
        
        bookings.add(booking);
        user.addBooking(booking);
        
        return booking;
    }
    
    public Booking createTrainBooking(User user, Train train, int numberOfPassengers, String seatClass) {
        bookingIdCounter++;
        String bookingId = "B" + bookingIdCounter;
        
        TrainBooking trainBooking = new TrainBooking("TB" + bookingIdCounter, train, numberOfPassengers, seatClass);
        Booking booking = new Booking(bookingId, user, trainBooking);
        
        // Calculate total price
        double totalPrice = train.getPrice() * numberOfPassengers;
        booking.setTotalPrice(totalPrice);
        
        bookings.add(booking);
        user.addBooking(booking);
        
        return booking;
    }
    
    public Booking createHotelBooking(User user, Hotel hotel, Room room, 
                                    String checkInDate, String checkOutDate, 
                                    int numberOfGuests, int numberOfRooms) {
        bookingIdCounter++;
        String bookingId = "B" + bookingIdCounter;
        
        HotelBooking hotelBooking = new HotelBooking("HB" + bookingIdCounter, hotel, room, 
                                                   checkInDate, checkOutDate, 
                                                   numberOfGuests, numberOfRooms);
        Booking booking = new Booking(bookingId, user, hotelBooking);
        
        // Calculate total price
        // In a real app, we would calculate the number of nights based on dates
        // For simplicity, we'll assume 3 nights
        int numberOfNights = 3;
        double totalPrice = room.getPrice() * numberOfNights * numberOfRooms;
        booking.setTotalPrice(totalPrice);
        
        bookings.add(booking);
        user.addBooking(booking);
        
        return booking;
    }
    
    public Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }
    
    public List<Booking> getBookingsByUser(User user) {
        return user.getBookings();
    }
    
    public boolean cancelBooking(Booking booking) {
        if (booking.getStatus().equals("Confirmed")) {
            booking.setStatus("Cancelled");
            
            // Handle the release of resources based on booking type
            BookingType type = booking.getType();
            
            if (type instanceof FlightBooking) {
                FlightBooking flightBooking = (FlightBooking) type;
                Flight flight = flightBooking.getFlight();
                flight.cancelBooking(flightBooking.getNumberOfPassengers());
            } else if (type instanceof TrainBooking) {
                TrainBooking trainBooking = (TrainBooking) type;
                Train train = trainBooking.getTrain();
                train.cancelBooking(trainBooking.getNumberOfPassengers());
            } else if (type instanceof HotelBooking) {
                HotelBooking hotelBooking = (HotelBooking) type;
                Room room = hotelBooking.getRoom();
                room.cancelBooking(hotelBooking.getCheckInDate(), hotelBooking.getCheckOutDate());
            }
            
            return true;
        }
        return false;
    }
    
    public Payment processPayment(Booking booking, String paymentMethod) {
        paymentIdCounter++;
        String paymentId = "P" + paymentIdCounter;
        
        Payment payment = new Payment(paymentId, booking.getTotalPrice(), paymentMethod);
        boolean success = payment.processPayment();
        
        if (success) {
            booking.setPayment(payment);
            booking.setStatus("Confirmed");
            return payment;
        }
        
        return null;
    }
}


class UserService {
    private List<User> users;
    private int userIdCounter;
    
    public UserService() {
        users = new ArrayList<>();
        userIdCounter = 1000;
        
        // Add a sample user
        registerUser("john_doe", "password123", "John Doe", "john@example.com", "123-456-7890");
    }
    
    public User registerUser(String username, String password, String name, String email, String phoneNumber) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return null; // Username already taken
            }
        }
        
        userIdCounter++;
        String userId = "U" + userIdCounter;
        
        User newUser = new User(userId, username, password, name, email, phoneNumber);
        users.add(newUser);
        
        return newUser;
    }
    
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean updateUserProfile(User user, String name, String email, String phoneNumber) {
        if (user != null) {
            if (name != null && !name.isEmpty()) {
                user.setName(name);
            }
            if (email != null && !email.isEmpty()) {
                user.setEmail(email);
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                user.setPhoneNumber(phoneNumber);
            }
            return true;
        }
        return false;
    }
}


// Main application class
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static SearchService searchService = new SearchService();
    private static BookingService bookingService = new BookingService();
    private static User currentUser = null;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public static void main(String[] args) {
        System.out.println("===== SMART TRAVEL BOOKING SYSTEM =====");
        
        int choice;
        do {
            displayMainMenu();
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                choice = 0;
                continue;
            }
            
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    if (currentUser != null) {
                        bookingMenu();
                    } else {
                        System.out.println("Please login first!");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        manageBookings();
                    } else {
                        System.out.println("Please login first!");
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        viewProfile();
                    } else {
                        System.out.println("Please login first!");
                    }
                    break;
                case 6:
                    if (currentUser != null) {
                        logout();
                    } else {
                        System.out.println("You are not logged in!");
                    }
                    break;
                case 7:
                    System.out.println("Thank you for using the Smart Travel Booking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Book Travel" + (currentUser == null ? " (Login Required)" : ""));
        System.out.println("4. Manage Bookings" + (currentUser == null ? " (Login Required)" : ""));
        System.out.println("5. View Profile" + (currentUser == null ? " (Login Required)" : ""));
        System.out.println("6. Logout" + (currentUser == null ? " (Login Required)" : ""));
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }
    
    private static void registerUser() {
        System.out.println("\n===== USER REGISTRATION =====");
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        User user = userService.registerUser(username, password, name, email, phoneNumber);
        
        if (user != null) {
            System.out.println("\nRegistration successful!");
            System.out.println("User ID: " + user.getUserId());
        } else {
            System.out.println("\nRegistration failed. Username already exists.");
        }
    }
    
    private static void login() {
        System.out.println("\n===== USER LOGIN =====");
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = userService.login(username, password);
        
        if (user != null) {
            currentUser = user;
            System.out.println("\nLogin successful!");
            System.out.println("Welcome, " + user.getName() + "!");
        } else {
            System.out.println("\nLogin failed. Invalid username or password.");
        }
    }
    
    private static void logout() {
        if (currentUser != null) {
            System.out.println("\nLogging out...");
            System.out.println("Goodbye, " + currentUser.getName() + "!");
            currentUser = null;
        } else {
            System.out.println("\nYou are not logged in!");
        }
    }
    
    private static void viewProfile() {
        if (currentUser != null) {
            currentUser.displayInfo();
            
            System.out.print("\nDo you want to update your profile? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();
            
            if (choice.equals("y") || choice.equals("yes")) {
                updateProfile();
            }
        } else {
            System.out.println("\nPlease login first!");
        }
    }
    
    private static void updateProfile() {
        System.out.println("\n===== UPDATE PROFILE =====");
        
        System.out.println("Current Information:");
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Phone: " + currentUser.getPhoneNumber());
        
        System.out.println("\nEnter new information (leave blank to keep current):");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        boolean success = userService.updateUserProfile(currentUser, name, email, phone);
        
        if (success) {
            System.out.println("\nProfile updated successfully!");
        } else {
            System.out.println("\nFailed to update profile.");
        }
    }
    
    private static void bookingMenu() {
        int choice;
        do {
            System.out.println("\n===== BOOKING MENU =====");
            System.out.println("1. Book a Flight");
            System.out.println("2. Book a Train");
            System.out.println("3. Book a Hotel");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice (1-4): ");
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                choice = 0;
                continue;
            }
            
            switch (choice) {
                case 1:
                    bookFlight();
                    break;
                case 2:
                    bookTrain();
                    break;
                case 3:
                    bookHotel();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
    
    private static void bookFlight() {
        System.out.println("\n===== FLIGHT BOOKING =====");
        
        System.out.print("Enter source city: ");
        String source = scanner.nextLine();
        
        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter travel date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        
        // Validate date format
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/mm/yyyy format.");
            return;
        }
        
        List<Flight> flights = searchService.searchFlights(source, destination, date);
        
        if (flights.isEmpty()) {
            System.out.println("\nNo flights found for your search criteria.");
            return;
        }
        
        System.out.println("\nAvailable Flights:");
        System.out.printf("%-8s %-15s %-25s %-15s %-15s %-10s %-8s%n", 
                       "ID", "Airline", "Flight", "Departure", "Arrival", "Price", "Seats");
        System.out.println("---------------------------------------------------------------------------------");
        
        for (Flight flight : flights) {
            System.out.printf("%-8s %-15s %-25s %-15s %-15s ₹%-9.2f %-8d%n", 
                           flight.getId(), flight.getAirline(), flight.getFlightNumber(),
                           flight.getDepartureTime(), flight.getArrivalTime(),
                           flight.getPrice(), flight.getAvailableSeats());
        }
        
        System.out.print("\nEnter flight ID to book (or 0 to cancel): ");
        String flightId = scanner.nextLine();
        
        if (flightId.equals("0")) {
            System.out.println("Booking cancelled.");
            return;
        }
        
        Flight selectedFlight = searchService.getFlightById(flightId);
        
        if (selectedFlight == null) {
            System.out.println("Invalid flight ID. Booking cancelled.");
            return;
        }
        
        selectedFlight.displayDetails();
        
        System.out.print("\nEnter number of passengers: ");
        int numberOfPassengers;
        try {
            numberOfPassengers = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Booking cancelled.");
            scanner.nextLine(); // Clear buffer
            return;
        }
        
        if (numberOfPassengers <= 0) {
            System.out.println("Number of passengers must be positive. Booking cancelled.");
            return;
        }
        
        if (selectedFlight.getAvailableSeats() < numberOfPassengers) {
            System.out.println("Not enough seats available. Booking cancelled.");
            return;
        }
        
        System.out.println("\nSelect seat class:");
        System.out.println("1. Economy");
        System.out.println("2. Business");
        System.out.println("3. First Class");
        System.out.print("Enter your choice (1-3): ");
        
        String seatClass;
        try {
            int classChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (classChoice) {
                case 1:
                    seatClass = "Economy";
                    break;
                case 2:
                    seatClass = "Business";
                    break;
                case 3:
                    seatClass = "First Class";
                    break;
                default:
                    System.out.println("Invalid choice. Using Economy class.");
                    seatClass = "Economy";
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Using Economy class.");
            scanner.nextLine(); // Clear buffer
            seatClass = "Economy";
        }
        
        // Create booking
        Booking booking = bookingService.createFlightBooking(currentUser, selectedFlight, numberOfPassengers, seatClass);
        
        // Add passenger names
        FlightBooking flightBooking = (FlightBooking) booking.getType();
        System.out.println("\nEnter passenger names:");
        for (int i = 0; i < numberOfPassengers; i++) {
            System.out.print("Passenger " + (i + 1) + ": ");
            String passengerName = scanner.nextLine();
            flightBooking.addPassengerName(passengerName);
        }
        
        // Display booking summary
        System.out.println("\n===== BOOKING SUMMARY =====");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Flight: " + selectedFlight.getAirline() + " " + selectedFlight.getFlightNumber());
        System.out.println("Route: " + selectedFlight.getSource() + " to " + selectedFlight.getDestination());
        System.out.println("Date: " + selectedFlight.getDepartureDate());
        System.out.println("Time: " + selectedFlight.getDepartureTime() + " - " + selectedFlight.getArrivalTime());
        System.out.println("Passengers: " + numberOfPassengers);
        System.out.println("Seat Class: " + seatClass);
        System.out.printf("Total Price: ₹%.2f%n", booking.getTotalPrice());
        System.out.println("============================");
        
        // Process payment
        processPayment(booking);
    }
    
    private static void bookTrain() {
        System.out.println("\n===== TRAIN BOOKING =====");
        
        System.out.print("Enter source city: ");
        String source = scanner.nextLine();
        
        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter travel date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        
        // Validate date format
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/mm/yyyy format.");
            return;
        }
        
        List<Train> trains = searchService.searchTrains(source, destination, date);
        
        if (trains.isEmpty()) {
            System.out.println("\nNo trains found for your search criteria.");
            return;
        }
        
        System.out.println("\nAvailable Trains:");
        System.out.printf("%-8s %-20s %-15s %-15s %-15s %-10s %-8s%n", 
                       "ID", "Name", "Number", "Departure", "Arrival", "Price", "Seats");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (Train train : trains) {
            System.out.printf("%-8s %-20s %-15s %-15s %-15s ₹%-9.2f %-8d%n", 
                           train.getId(), train.getName(), train.getTrainNumber(),
                           train.getDepartureTime(), train.getArrivalTime(),
                           train.getPrice(), train.getAvailableSeats());
        }
        
        System.out.print("\nEnter train ID to book (or 0 to cancel): ");
        String trainId = scanner.nextLine();
        
        if (trainId.equals("0")) {
            System.out.println("Booking cancelled.");
            return;
        }
        
        Train selectedTrain = searchService.getTrainById(trainId);
        
        if (selectedTrain == null) {
            System.out.println("Invalid train ID. Booking cancelled.");
            return;
        }
        
        selectedTrain.displayDetails();
        
        System.out.print("\nEnter number of passengers: ");
        int numberOfPassengers;
        try {
            numberOfPassengers = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Booking cancelled.");
            scanner.nextLine(); // Clear buffer
            return;
        }
        
        if (numberOfPassengers <= 0) {
            System.out.println("Number of passengers must be positive. Booking cancelled.");
            return;
        }
        
        if (selectedTrain.getAvailableSeats() < numberOfPassengers) {
            System.out.println("Not enough seats available. Booking cancelled.");
            return;
        }
        
        System.out.println("\nSelect seat class:");
        System.out.println("1. General");
        System.out.println("2. Sleeper");
        System.out.println("3. AC");
        System.out.print("Enter your choice (1-3): ");
        
        String seatClass;
        try {
            int classChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (classChoice) {
                case 1:
                    seatClass = "General";
                    break;
                case 2:
                    seatClass = "Sleeper";
                    break;
                case 3:
                    seatClass = "AC";
                    break;
                default:
                    System.out.println("Invalid choice. Using General class.");
                    seatClass = "General";
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Using General class.");
            scanner.nextLine(); // Clear buffer
            seatClass = "General";
        }
        
        // Create booking
        Booking booking = bookingService.createTrainBooking(currentUser, selectedTrain, numberOfPassengers, seatClass);
        
        // Add passenger names
        TrainBooking trainBooking = (TrainBooking) booking.getType();
        System.out.println("\nEnter passenger names:");
        for (int i = 0; i < numberOfPassengers; i++) {
            System.out.print("Passenger " + (i + 1) + ": ");
            String passengerName = scanner.nextLine();
            trainBooking.addPassengerName(passengerName);
        }
        
        // Display booking summary
        System.out.println("\n===== BOOKING SUMMARY =====");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Train: " + selectedTrain.getName() + " (" + selectedTrain.getTrainNumber() + ")");
        System.out.println("Route: " + selectedTrain.getSource() + " to " + selectedTrain.getDestination());
        System.out.println("Date: " + selectedTrain.getDepartureDate());
        System.out.println("Time: " + selectedTrain.getDepartureTime() + " - " + selectedTrain.getArrivalTime());
        System.out.println("Passengers: " + numberOfPassengers);
        System.out.println("Seat Class: " + seatClass);
        System.out.printf("Total Price: ₹%.2f%n", booking.getTotalPrice());
        System.out.println("============================");
        
        // Process payment
        processPayment(booking);
    }
    
    private static void bookHotel() {
        System.out.println("\n===== HOTEL BOOKING =====");
        
        System.out.print("Enter city: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter check-in date (dd/mm/yyyy): ");
        String checkInDate = scanner.nextLine();
        
        System.out.print("Enter check-out date (dd/mm/yyyy): ");
        String checkOutDate = scanner.nextLine();
        
        // Validate date format
        try {
            Date checkIn = dateFormat.parse(checkInDate);
            Date checkOut = dateFormat.parse(checkOutDate);
            
            if (checkOut.before(checkIn)) {
                System.out.println("Check-out date cannot be before check-in date.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/mm/yyyy format.");
            return;
        }
        
        List<Hotel> hotels = searchService.searchHotels(location, checkInDate, checkOutDate);
        
        if (hotels.isEmpty()) {
            System.out.println("\nNo hotels found for your search criteria.");
            return;
        }
        
        System.out.println("\nAvailable Hotels:");
        System.out.printf("%-8s %-30s %-10s %-10s%n", 
                       "ID", "Name", "Rating", "Stars");
        System.out.println("-----------------------------------------------------------");
        
        for (Hotel hotel : hotels) {
            System.out.printf("%-8s %-30s %-10.1f %-10d%n", 
                           hotel.getHotelId(), hotel.getName(), 
                           hotel.getRating(), hotel.getStarRating());
        }
        
        System.out.print("\nEnter hotel ID to book (or 0 to cancel): ");
        String hotelId = scanner.nextLine();
        
        if (hotelId.equals("0")) {
            System.out.println("Booking cancelled.");
            return;
        }
        
        Hotel selectedHotel = searchService.getHotelById(hotelId);
        
        if (selectedHotel == null) {
            System.out.println("Invalid hotel ID. Booking cancelled.");
            return;
        }
        
        selectedHotel.displayDetails();
        
        // Get available rooms
        List<Room> availableRooms = selectedHotel.getAvailableRooms(checkInDate, checkOutDate);
        
        System.out.println("\nAvailable Room Types:");
        System.out.printf("%-8s %-15s %-15s%n", "ID", "Type", "Price/Night");
        System.out.println("--------------------------------------");
        
        for (Room room : availableRooms) {
            System.out.printf("%-8s %-15s ₹%-14.2f%n", 
                           room.getRoomId(), room.getType(), room.getPrice());
        }
        
        System.out.print("\nEnter room ID to book (or 0 to cancel): ");
        String roomId = scanner.nextLine();
        
        if (roomId.equals("0")) {
            System.out.println("Booking cancelled.");
            return;
        }
        
        Room selectedRoom = searchService.getRoomById(selectedHotel, roomId);
        
        if (selectedRoom == null) {
            System.out.println("Invalid room ID. Booking cancelled.");
            return;
        }
        
        System.out.print("\nEnter number of guests: ");
        int numberOfGuests;
        try {
            numberOfGuests = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Booking cancelled.");
            scanner.nextLine(); // Clear buffer
            return;
        }
        
        System.out.print("Enter number of rooms: ");
        int numberOfRooms;
        try {
            numberOfRooms = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Booking cancelled.");
            scanner.nextLine(); // Clear buffer
            return;
        }
        
        if (numberOfGuests <= 0 || numberOfRooms <= 0) {
            System.out.println("Number of guests and rooms must be positive. Booking cancelled.");
            return;
        }
        
        // Create booking
        Booking booking = bookingService.createHotelBooking(currentUser, selectedHotel, selectedRoom, 
                                                          checkInDate, checkOutDate, 
                                                          numberOfGuests, numberOfRooms);
        
        // Add guest names
        HotelBooking hotelBooking = (HotelBooking) booking.getType();
        System.out.println("\nEnter primary guest name:");
        System.out.print("Guest: ");
        String guestName = scanner.nextLine();
        hotelBooking.addGuestName(guestName);
        
        // Display booking summary
        System.out.println("\n===== BOOKING SUMMARY =====");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Hotel: " + selectedHotel.getName() + " (" + selectedHotel.getLocation() + ")");
        System.out.println("Room Type: " + selectedRoom.getType());
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Guests: " + numberOfGuests);
        System.out.println("Rooms: " + numberOfRooms);
        System.out.printf("Price per Night: ₹%.2f%n", selectedRoom.getPrice());
        System.out.printf("Total Price: ₹%.2f%n", booking.getTotalPrice());
        System.out.println("============================");
        
        // Process payment
        processPayment(booking);
    }
    
    private static void processPayment(Booking booking) {
        System.out.println("\n===== PAYMENT PROCESS =====");
        System.out.printf("Total Amount: ₹%.2f%n", booking.getTotalPrice());
        
        System.out.println("\nSelect payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. UPI");
        System.out.println("4. Cancel Booking");
        System.out.print("Enter your choice (1-4): ");
        
        try {
            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            String paymentMethod;
            switch (paymentChoice) {
                case 1:
                    paymentMethod = "Credit Card";
                    break;
                case 2:
                    paymentMethod = "Debit Card";
                    break;
                case 3:
                    paymentMethod = "UPI";
                    break;
                case 4:
                    System.out.println("Booking cancelled.");
                    return;
                default:
                    System.out.println("Invalid choice. Booking cancelled.");
                    return;
            }
            
            // For credit/debit card, ask for details
            if (paymentChoice == 1 || paymentChoice == 2) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                
                System.out.print("Enter expiry date (MM/YY): ");
                String expiryDate = scanner.nextLine();
                
                System.out.print("Enter CVV: ");
                String cvv = scanner.nextLine();
                
                // In a real app, we would validate these details
            } else if (paymentChoice == 3) {
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine();
                
                // In a real app, we would validate this
            }
            
            Payment payment = bookingService.processPayment(booking, paymentMethod);
            
            if (payment != null) {
                System.out.println("\nPayment successful!");
                System.out.println("Transaction ID: " + payment.getTransactionId());
                System.out.println("Your booking is confirmed.");
            } else {
                System.out.println("\nPayment failed. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Payment cancelled.");
            scanner.nextLine(); // Clear buffer
        }
    }
    
    private static void manageBookings() {
        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }
        
        List<Booking> userBookings = bookingService.getBookingsByUser(currentUser);
        
        if (userBookings.isEmpty()) {
            System.out.println("\nYou have no bookings yet.");
            return;
        }
        
        System.out.println("\n===== YOUR BOOKINGS =====");
        System.out.printf("%-10s %-15s %-30s %-15s%n", 
                       "ID", "Type", "Details", "Status");
        System.out.println("--------------------------------------------------------------");
        
        for (Booking booking : userBookings) {
            String details = "";
            BookingType type = booking.getType();
            
            if (type instanceof FlightBooking) {
                FlightBooking flightBooking = (FlightBooking) type;
                Flight flight = flightBooking.getFlight();
                details = flight.getSource() + " to " + flight.getDestination() + " on " + flight.getDepartureDate();
            } else if (type instanceof TrainBooking) {
                TrainBooking trainBooking = (TrainBooking) type;
                Train train = trainBooking.getTrain();
                details = train.getSource() + " to " + train.getDestination() + " on " + train.getDepartureDate();
            } else if (type instanceof HotelBooking) {
                HotelBooking hotelBooking = (HotelBooking) type;
                Hotel hotel = hotelBooking.getHotel();
                details = hotel.getName() + " in " + hotel.getLocation() + " from " + hotelBooking.getCheckInDate();
            }
            
            System.out.printf("%-10s %-15s %-30s %-15s%n", 
                           booking.getBookingId(), type.getTypeName(), details, booking.getStatus());
        }
        
        System.out.println("\nOptions:");
        System.out.println("1. View Booking Details");
        System.out.println("2. Cancel a Booking");
        System.out.println("3. Return to Main Menu");
        System.out.print("Enter your choice (1-3): ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    viewBookingDetails();
                    break;
                case 2:
                    cancelBooking();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Returning to main menu...");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Returning to main menu...");
            scanner.nextLine(); // Clear buffer
        }
    }
    
    private static void viewBookingDetails() {
        System.out.print("\nEnter booking ID: ");
        String bookingId = scanner.nextLine();
        
        Booking booking = bookingService.findBookingById(bookingId);
        
        if (booking != null && booking.getUser().getUserId().equals(currentUser.getUserId())) {
            booking.displayBookingDetails();
        } else {
            System.out.println("Booking not found.");
        }
    }
    
    private static void cancelBooking() {
        System.out.print("\nEnter booking ID to cancel: ");
        String bookingId = scanner.nextLine();
        
        Booking booking = bookingService.findBookingById(bookingId);
        
        if (booking != null && booking.getUser().getUserId().equals(currentUser.getUserId())) {
            if (booking.getStatus().equals("Confirmed")) {
                System.out.print("Are you sure you want to cancel this booking? (y/n): ");
                String confirm = scanner.nextLine().toLowerCase();
                
                if (confirm.equals("y") || confirm.equals("yes")) {
                    boolean success = bookingService.cancelBooking(booking);
                    if (success) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel booking.");
                    }
                } else {
                    System.out.println("Cancellation aborted.");
                }
            } else {
                System.out.println("This booking cannot be cancelled (status: " + booking.getStatus() + ").");
            }
        } else {
            System.out.println("Booking not found.");
        }
    }
}