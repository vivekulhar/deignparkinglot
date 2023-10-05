package dev.vivek;


import dev.vivek.controllers.TicketController;
import dev.vivek.dtos.GenerateTicketRequestDTO;
import dev.vivek.dtos.GenerateTicketResponseDTO;
import dev.vivek.dtos.ResponseStatus;
import dev.vivek.models.enums.VehicleType;
import dev.vivek.repositories.*;
import dev.vivek.services.GateService;
import dev.vivek.services.TicketService;
import dev.vivek.services.VehicleService;
import dev.vivek.services.strategies.BoothAllocationStrategy;
import dev.vivek.services.strategies.NearestEmptyBoothStrategy;

public class Main {
    public static void main(String[] args) {

        TicketRepository ticketRepository = new TicketRepository();

        GateRepository gateRepository = new GateRepository();
        GateService gateService = new GateService(gateRepository);

        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleService vehicleService = new VehicleService(vehicleRepository);

        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        BoothRepository boothRepository = new BoothRepository();
        BoothAllocationStrategy boothAllocationStrategy = new NearestEmptyBoothStrategy(parkingLotRepository, boothRepository);

        TicketService ticketService = new TicketService(ticketRepository, gateService, vehicleService, boothAllocationStrategy);
        TicketController ticketController = new TicketController(ticketService);

        GenerateTicketRequestDTO    generateTicketRequestDTO = new GenerateTicketRequestDTO();
        generateTicketRequestDTO.setGateId(1L);
        generateTicketRequestDTO.setVehicleNumber("KA-01-HH-1234");
        generateTicketRequestDTO.setVehicleType(VehicleType.MEDIUM);

        GenerateTicketResponseDTO generateTicketResponseDTO = ticketController.generateTicket(generateTicketRequestDTO);

        if(generateTicketResponseDTO.getStatus().equals(ResponseStatus.FAILURE)) {
            System.out.println("Ticket generation failed: "+generateTicketResponseDTO.getMessage());
        }else{
            System.out.println("Ticket generated successfully with id: "+generateTicketResponseDTO.getGeneratedTicketId());
        }
    }
}