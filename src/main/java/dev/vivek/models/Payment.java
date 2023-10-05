package dev.vivek.models;

import dev.vivek.models.enums.PaymentMode;
import dev.vivek.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel{
    private int amount;
    private Bill bill;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
}
