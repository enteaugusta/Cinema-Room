package com.example.cinemaroom;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cinemaroom.Cinema.getAllSeats;

@RestController
public class Controller {
	
	private final Cinema cinema;
	
	public Controller() {
		this.cinema = getAllSeats(9,9);
	}
	
	@GetMapping("/seats")
	public Cinema getSeats() {
		return cinema;
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<?> purchaseTicket(@RequestBody Seats seatToBook) {
		if (seatToBook.getColumn() < 1
				|| seatToBook.getRow() < 1
				|| seatToBook.getColumn() > cinema.getTotalColumns()
				|| seatToBook.getRow() > cinema.getTotalRows()) {
			return new ResponseEntity<>(
					Map.of( "error","The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
		}
		for (int i = 0; i < cinema.getAvailableSeats().size(); i++){
			Seats availableSeat = cinema.getAvailableSeats().get(i);
			if (availableSeat.getRow() == seatToBook.getRow() && availableSeat.getColumn() == seatToBook.getColumn()) {
				Tickets ticket = new Tickets(UUID.randomUUID(), availableSeat);
				cinema.getOrdered_seats().add(ticket);
				cinema.getAvailableSeats().remove(i);
				return new ResponseEntity<>(availableSeat, HttpStatus.OK);
				
			}
		}
		return new ResponseEntity<>(Map.of("error","The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
	}
	
}
