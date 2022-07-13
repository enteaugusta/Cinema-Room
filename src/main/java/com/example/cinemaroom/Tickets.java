package com.example.cinemaroom;

import java.util.UUID;

public class Tickets {
	
	private UUID uuid;
	private Seats ticket;
	
	public Tickets(UUID uuid, Seats ticket){
		this.uuid = uuid;
		this.ticket = ticket;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Seats getTicket() {
		return ticket;
	}
	
	public void setTicket(Seats ticket) {
		this.ticket = ticket;
	}
}
