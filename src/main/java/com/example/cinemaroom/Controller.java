package com.example.cinemaroom;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	
}
