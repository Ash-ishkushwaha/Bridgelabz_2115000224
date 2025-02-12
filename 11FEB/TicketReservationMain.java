import java.time.LocalDateTime;

class Ticket {
    private int ticketId;
    private String customerName;
    private String movieName;
    private String seatNumber;
    private LocalDateTime bookingTime;
    private Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, LocalDateTime bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieName() {
        return movieName;
    }

    public Ticket getNext() {
        return next;
    }

    public void setNext(Ticket next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Customer Name: " + customerName + ", Movie Name: " + movieName + ", Seat Number: " + seatNumber + ", Booking Time: " + bookingTime;
    }
}

class CircularLinkedListReservation {
    private Ticket head;
    private Ticket tail;
    private int ticketCount;

    public CircularLinkedListReservation() {
        this.head = null;
        this.tail = null;
        this.ticketCount = 0;
    }

    public void addTicketAtEnd(int ticketId, String customerName, String movieName, String seatNumber, LocalDateTime bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.setNext(newTicket);
        } else {
            newTicket.setNext(head);
            tail.setNext(newTicket);
            tail = newTicket;
        }
        ticketCount++;
    }

    public void removeTicketById(int ticketId) {
        if (head == null) return;
        if (head.getTicketId() == ticketId) {
            if (head == tail) {
                head = tail = null;
            } else {
                Ticket temp = head;
                while (temp.getNext() != head) {
                    temp = temp.getNext();
                }
                temp.setNext(head.getNext());
                head = head.getNext();
            }
            ticketCount--;
            return;
        }
        Ticket current = head;
        while (current.getNext() != head && current.getNext().getTicketId() != ticketId) {
            current = current.getNext();
        }
        if (current.getNext().getTicketId() == ticketId) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == head) {
                tail = current;
            }
            ticketCount--;
        }
    }

    public void displayAllTickets() {
        if (head == null) return;
        Ticket current = head;
        do {
            System.out.println(current);
            current = current.getNext();
        } while (current != head);
    }

    public Ticket searchTicketByCustomerName(String customerName) {
        if (head == null) return null;
        Ticket current = head;
        do {
            if (current.getCustomerName().equalsIgnoreCase(customerName)) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    public Ticket searchTicketByMovieName(String movieName) {
        if (head == null) return null;
        Ticket current = head;
        do {
            if (current.getMovieName().equalsIgnoreCase(movieName)) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    public int getTotalTickets() {
        return ticketCount;
    }
}

public class TicketReservationMain {
    public static void main(String[] args) {
        CircularLinkedListReservation reservationSystem = new CircularLinkedListReservation();
        reservationSystem.addTicketAtEnd(1, "Alice", "Inception", "A1", LocalDateTime.now());
        reservationSystem.addTicketAtEnd(2, "Bob", "Avatar", "B2", LocalDateTime.now());
        reservationSystem.addTicketAtEnd(3, "Charlie", "The Matrix", "C3", LocalDateTime.now());

        System.out.println("Displaying all tickets:");
        reservationSystem.displayAllTickets();
        System.out.println();

        System.out.println("Searching for ticket by customer name 'Alice':");
        Ticket ticket = reservationSystem.searchTicketByCustomerName("Alice");
        if (ticket != null) {
            System.out.println(ticket);
        } else {
            System.out.println("Ticket not found.");
        }
        System.out.println();

        System.out.println("Removing ticket with ID 2:");
        reservationSystem.removeTicketById(2);
        reservationSystem.displayAllTickets();
        System.out.println();

        System.out.println("Total number of booked tickets:");
        System.out.println(reservationSystem.getTotalTickets());
    }
}