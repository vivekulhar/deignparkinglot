package dev.vivek.repositories;

import dev.vivek.models.Ticket;

import java.util.HashMap;

public class TicketRepository {
    private HashMap<Long, Ticket> ticketTableMock = new HashMap<>();
    private Long autoIncrementIdMock = 0L;

    public Ticket save(Ticket ticket){
        if(ticket.getId()==null){
            autoIncrementIdMock++;
            ticket.setId(autoIncrementIdMock);
            ticketTableMock.put(autoIncrementIdMock, ticket);
        }else{
            ticketTableMock.put(ticket.getId(), ticket);
        }
        return ticket;
    }
}
