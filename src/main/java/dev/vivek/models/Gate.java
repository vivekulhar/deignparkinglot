package dev.vivek.models;

import dev.vivek.models.enums.GateType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Gate extends BaseModel{
    private String number;
    private GateType gateType;
    private Operator currentOperator;
    private ParkingLot parkingLot;
}
