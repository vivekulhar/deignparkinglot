package dev.vivek.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ticket extends BaseModel{
    private Date entryTime;
    private Gate entryGate;
    private Vehicle vehicle;
    private Operator operator;
    private Booth assignedBooth;
}
