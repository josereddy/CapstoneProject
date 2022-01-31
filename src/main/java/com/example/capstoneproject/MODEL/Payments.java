package com.example.capstoneproject.MODEL;

import com.example.capstoneproject.DTO.Order_DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Payment_id")
    private Integer payment_id;
    private String order_payment_method;
    private String  order_payment_confirmation_number;

}
