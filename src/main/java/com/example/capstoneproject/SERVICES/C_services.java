package com.example.capstoneproject.SERVICES;

import com.example.capstoneproject.DTO.Order_DTO;
import com.example.capstoneproject.DTO.Order_Item_DTO;
import com.example.capstoneproject.DTO.Order_Payment_DTO;
import com.example.capstoneproject.DTO.Order_Update;
import com.example.capstoneproject.MODEL.Items;
import com.example.capstoneproject.MODEL.Orders;
import com.example.capstoneproject.MODEL.Payments;
import com.example.capstoneproject.REPOSITORY.OrdersRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class C_services {


    private static final Logger log = LogManager.getLogger(C_services.class.getName());


    @Autowired
    private OrdersRepository or_repo;

    public Orders orders;

    public Orders Dto_Entity(Order_DTO or)
    {
        List<Items> items_list = new LinkedList<>();
        List<Payments> payments_list = new LinkedList<>();

        Items item ;
        for(Order_Item_DTO or_item:or.getItems())
        {
            item=new Items();
            item.setItem_id(0);
            item.setOrder_item_name(or_item.getOrder_item_name());
            item.setOrder_item_qty(or_item.getOrder_item_qty());
            items_list.add(item);
        }

        Payments payment;
        for(Order_Payment_DTO or_payment:or.getPayments())
        {
            payment = new Payments();
            payment.setPayment_id(0);
            payment.setOrder_payment_method(or_payment.getOrder_payment_method());
            payment.setOrder_payment_confirmation_number(or_payment.getOrder_payment_confirmation_number());
            payments_list.add(payment);
        }



        log.info("Inside DTO_ENTITY CONVERSION SERVICE");
         orders = new Orders();
         orders.setOrder_id(0);
        orders.setOrder_status(or.getOrder_status());
        orders.setCustomer_id(or.getCustomer_id());
        orders.setOrder_subtotal(or.getOrder_subtotal());
        orders.setOrder_tax(or.getOrder_tax());
        orders.setOrder_shipping_charges(or.getOrder_shipping_charges());
        orders.setOrder_total(or.getOrder_total());
        orders.setOrder_billing_address(or.getOrder_billing_address());
        orders.setOrder_billing_city(or.getOrder_billing_city());
        orders.setOrder_billing_zip(or.getOrder_billing_zip());
        orders.setOrder_shipping_aline(or.getOrder_shipping_aline());
        orders.setOrder_shipping_state(or.getOrder_shipping_state());
        orders.setOrder_shipping_city(or.getOrder_shipping_city());
        orders.setOrder_shipping_zip(or.getOrder_shipping_zip());
        orders.setItems(items_list);
        orders.setPayments(payments_list);





        return orders;
    }

    public boolean check(Order_DTO or)
    {

        log.info("Inside CHECKING FOR VALID ORDER");
        if( or.getOrder_status()==null || or.getCustomer_id()==null||
                or.getOrder_tax()==null || or.getOrder_shipping_charges()==null|| or.getOrder_total()==null|| or.getOrder_billing_address()==null ||
                or.getOrder_billing_city()==null|| or.getOrder_billing_zip()==null|| or.getOrder_shipping_aline()==null||
                or.getOrder_shipping_state()==null|| or.getOrder_shipping_city()==null|| or.getOrder_shipping_zip()==null||or.getItems()==null|| or.getPayments()==null
        ) {
            return true;
        }
        else
            return false;


    }

    public List<Order_Update> check_batch(List<Order_Update> batch_order) {

        log.info("Inside c_Service ti check the BATCH_ORDERS" );
        List<Order_Update> new_valid_order = batch_order.stream().filter(c -> {
            Optional<Orders> order = or_repo.findById(c.getOrder_id());
            return ((order.isPresent()) ? true : false);
        }).collect(Collectors.toList());
    return new_valid_order;
    }

}
