package com.example.cinemaroom;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cinema {
	
	private int total_rows;
	private int total_columns;
	private List<Seats> available_seats;
	@JsonIgnore
	private List<Tickets> ordered_seats;
	
	
	public Cinema(int total_rows, int total_columns, List<Seats> availableSeats) {
		this.total_rows = total_rows;
		this.total_columns = total_columns;
		this.available_seats = availableSeats;
		this.ordered_seats = new ArrayList<>();
	}
	
	public static Cinema getAllSeats(int rows, int columns){
		List<Seats> seats = new ArrayList<>();
		for (int row = 1; row <= rows; row++ ) {
			for (int column = 1; column <= columns; column++){
				int price = determineSeatPrice(row);
				seats.add(new Seats(row, column, price));
			}
		}
		return new Cinema(rows, columns, seats);
	}
	
	
	private static int determineSeatPrice(int row) {
		int price;
		if (row <= 4) {
			price = 10;
		} else {
			price = 8;
		}
		return price;
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
	public List<Seats> getAvailableSeats() {
		return available_seats;
	}
	
	
	public void setAvailableSeats(List<Seats> available_seats) {
		this.available_seats = available_seats;
	}
	
	
	public List<Tickets> getOrdered_seats() {
		return ordered_seats;
	}
	
	
	public void setOrdered_seats(List<Tickets> ordered_seats) {
		this.ordered_seats = ordered_seats;
	}
}
