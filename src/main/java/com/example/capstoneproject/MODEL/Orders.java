package com.example.capstoneproject.MODEL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Schema(description = "Must be filled")
public class Orders extends Base_Order {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "or_items")
    private List<Items> items;
    @OneToMany(targetEntity = Payments.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "or_items",referencedColumnName = "order_id")
    private List<Payments> payments;


}
