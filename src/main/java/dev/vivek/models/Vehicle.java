package dev.vivek.models;

import dev.vivek.models.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle extends BaseModel{
    private VehicleType vehicleType;
    private String vehicleNumber;
}
