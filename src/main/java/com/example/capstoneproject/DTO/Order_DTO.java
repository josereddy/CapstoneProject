package com.example.capstoneproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_DTO {

    private String order_status;
    private Integer customer_id;
    private Integer order_subtotal;
    private Integer order_tax;
    private Integer order_shipping_charges;
    private Integer order_total;
    private String order_billing_address;
    private String order_billing_city;
    private String order_billing_zip;
    private String order_shipping_aline;
    private String order_shipping_city;
    private String order_shipping_state;
    private String order_shipping_zip;
    private List<Order_Item_DTO> items;
    private List<Order_Payment_DTO> payments;





}
