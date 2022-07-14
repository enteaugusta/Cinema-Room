package com.example.cinemaroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cinemaroom.Cinema.getAllSeats;

@RestController
public class Controller {

    private final Cinema cinema;


    public Controller() {
        int NUMBER_OF_SEATS_PER_ROW = 9;
        int NUMBER_OF_ROWS = 9;
        this.cinema = getAllSeats(NUMBER_OF_ROWS, NUMBER_OF_SEATS_PER_ROW);
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket( @RequestBody Seats seatToBook ) {
        if (seatToBook.getColumn() < 1
                || seatToBook.getRow() < 1
                || seatToBook.getColumn() > cinema.getTotalColumns()
                || seatToBook.getRow() > cinema.getTotalRows()) {
            return new ResponseEntity<>(
                    Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < cinema.getAvailableSeats().size(); i++) {
            Seats availableSeat = cinema.getAvailableSeats().get(i);
            if (availableSeat.getRow() == seatToBook.getRow() && availableSeat.getColumn() == seatToBook.getColumn()) {
                Tickets ticket = new Tickets(UUID.randomUUID(), availableSeat);
                cinema.getOrderedSeats().add(ticket);
                cinema.getAvailableSeats().remove(i);
                return new ResponseEntity<>(ticket, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> purchaseTicket( @RequestBody Token token ) {
        List<Tickets> tickets = cinema.getOrderedSeats();
        for (Tickets ticket : tickets) {
            if (ticket.getUuid().equals(token.getToken())) {
                tickets.remove(ticket);
                cinema.getAvailableSeats().add(ticket.getTicket());
                return new ResponseEntity<>(Map.of("returned_ticket", ticket.getTicket()), HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> printStatistics( @RequestParam(required = false) String password ) {

        if ((password != null) && password.equals("super_secret")) {
            Map<String, Integer> statistics = getStatistics();
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }


    private Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("current_income", getCurrentIncome());
        statistics.put("number_of_available_seats", getNumberOfAvailableSeats());
        statistics.put("number_of_purchased_tickets", getNumberOfPurchasedTickets());
        return statistics;
    }


    public int getCurrentIncome() {
        int currentIncome = 0;
        if (cinema.getOrderedSeats() != null) {
            for (Tickets ticket : cinema.getOrderedSeats()) {
                int income = ticket.getTicket().getPrice();
                currentIncome += income;
            }
        }
        return currentIncome;
    }


    public int getNumberOfAvailableSeats() {
        int numberOFAvailableSeats = 0;
        if (cinema.getAvailableSeats() != null) {
            numberOFAvailableSeats = cinema.getAvailableSeats().size();
        }
        return numberOFAvailableSeats;
    }


    public int getNumberOfPurchasedTickets() {
        int numberOfPurchasedTickets = 0;
        if (cinema.getOrderedSeats() != null) {
            numberOfPurchasedTickets = cinema.getOrderedSeats().size();
        }
        return numberOfPurchasedTickets;
    }

}
