package dev.vivek.controllers;

import dev.vivek.dtos.GenerateTicketRequestDTO;
import dev.vivek.dtos.GenerateTicketResponseDTO;
import dev.vivek.dtos.ResponseStatus;
import dev.vivek.models.Ticket;
import dev.vivek.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDTO generateTicket(GenerateTicketRequestDTO generateTicketRequestDTO) {
        GenerateTicketResponseDTO responseDTO = new GenerateTicketResponseDTO();
        try{
            Ticket ticket = ticketService.generateTicket(generateTicketRequestDTO.getGateId(),
                    generateTicketRequestDTO.getVehicleNumber(),
                    generateTicketRequestDTO.getVehicleType());
            responseDTO.setGeneratedTicketId(ticket.getId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Ticket generated successfully");
        } catch(Exception ex) {
            // ideally don't give exception message, might leak application details
            // log it, give the client id of log
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }
}
