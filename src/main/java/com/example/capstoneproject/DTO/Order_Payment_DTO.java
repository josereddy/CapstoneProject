package com.example.capstoneproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_Payment_DTO {
    private String order_payment_method;
    private String  order_payment_confirmation_number;

}
