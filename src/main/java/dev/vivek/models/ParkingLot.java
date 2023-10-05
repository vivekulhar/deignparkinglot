package dev.vivek.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingLot extends BaseModel{
    private String name;
    private String address;
    private Integer capacity;

    private List<Booth> booths;
    private List<Gate> gates;
}
