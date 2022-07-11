package com.example.cinemaroom;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.NonNull;

public class Seats {
	
	@NonNull
	private int row;
	@NonNull
	private int column;
	
	
	public Seats(int row, int column) {
		this.row = row;
		this.column = column;
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
}
