package dev.vivek.services;

import dev.vivek.exceptions.BoothNotAvailableException;
import dev.vivek.exceptions.GateNotFoundException;
import dev.vivek.models.Booth;
import dev.vivek.models.Gate;
import dev.vivek.models.Ticket;
import dev.vivek.models.Vehicle;
import dev.vivek.models.enums.VehicleType;
import dev.vivek.repositories.TicketRepository;
import dev.vivek.services.strategies.BoothAllocationStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private TicketRepository ticketRepository;
    private GateService gateService;
    private VehicleService vehicleService;
    private BoothAllocationStrategy boothAllocationStrategy;

    public TicketService(TicketRepository ticketRepository, GateService gateService, VehicleService vehicleService, BoothAllocationStrategy boothAllocationStrategy) {
        this.ticketRepository = ticketRepository;
        this.gateService = gateService;
        this.vehicleService = vehicleService;
        this.boothAllocationStrategy = boothAllocationStrategy;
    }
    public Ticket generateTicket(Long gateId, String vehicleNumber,
                                 VehicleType vehicleType) throws
            GateNotFoundException, BoothNotAvailableException {
        // get the gate object ==> gateservice
        Gate gate = gateService.getGateById(gateId);
        // get or register the vehicle => vehicleservice
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber, vehicleType);
        //asign booth using appropriate strategy
        Optional<Booth> assignedBoothWrapper = boothAllocationStrategy.assignBooth(vehicle.getVehicleType(), gate);
        if(assignedBoothWrapper.isEmpty()){
            throw new BoothNotAvailableException("No booth available for vehicle type: "+vehicle.getVehicleType());
        }
        //create ticket object
        Ticket ticket = new Ticket();
        ticket.setEntryGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setAssignedBooth(assignedBoothWrapper.get());
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getCurrentOperator());

        // save ticket object in db
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}
