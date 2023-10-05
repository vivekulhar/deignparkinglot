package dev.vivek.dtos;

import dev.vivek.models.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTicketRequestDTO {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long gateId;
}
