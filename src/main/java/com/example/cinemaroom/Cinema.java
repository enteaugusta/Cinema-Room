package com.example.cinemaroom;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Cinema {
	
	private int total_rows;
	private int total_columns;
	private List<Seat> available_seats;
	
	
	public Cinema(int total_rows, int total_columns, List<Seat> availableSeats) {
		this.total_rows = total_rows;
		this.total_columns = total_columns;
		this.available_seats = availableSeats;
	}
	
	public static Cinema getAllSeats(int rows, int columns){
		List<Seat> seats = new ArrayList<>();
		for (int row = 1; row <= rows; row++ ) {
			for (int column = 1; column <= columns; column++){
				seats.add(new Seat(row, column));
			}
		}
		return new Cinema(rows, columns, seats);
	}
	
	@JsonGetter(value = "total_rows")
	public int getTotalRows() {
		return total_rows;
	}
	
	
	public void setTotalRows(int total_rows) {
		this.total_rows = total_rows;
	}
	
	@JsonGetter(value = "seats_per_row")
	public int getTotalColumns() {
		return total_columns;
	}
	
	
	public void setTotalColumns(int total_columns) {
		this.total_columns = total_columns;
	}
	
	@JsonGetter(value = "available_seats")
	public List<Seat> getAvailableSeats() {
		return available_seats;
	}
	
	
	public void setAvailableSeats(List<Seat> available_seats) {
		this.available_seats = available_seats;
	}
}
