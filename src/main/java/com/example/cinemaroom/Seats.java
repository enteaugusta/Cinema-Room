package com.example.cinemaroom;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.NonNull;

public class Seats {
	
	@NonNull
	private int row;
	@NonNull
	private int column;
	private int price;
	
	public Seats(int row, int column, int price) {
		this.row = row;
		this.column = column;
		this.price = price;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	@JsonGetter(value = "seat")
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
