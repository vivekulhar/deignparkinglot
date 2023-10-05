package dev.vivek.models;

import dev.vivek.models.enums.ParkingBoothStatus;
import dev.vivek.models.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booth extends BaseModel{
    private String number;
    private VehicleType supportedVehicleType;
    private ParkingBoothStatus boothStatus;
    private ParkingLot parkingLot;
}
