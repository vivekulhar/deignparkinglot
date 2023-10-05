package dev.vivek.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class Bill extends BaseModel{
    private Date entryTime;
    private Gate exitGate;
    private Operator operator;
    private Ticket ticket;
    private int amount;
}
