package com.example.capstoneproject.CONTROLLER;


import com.example.capstoneproject.DTO.Order_DTO;
import com.example.capstoneproject.DTO.Order_Update;
import com.example.capstoneproject.Exception.UserNotFoundException;
import com.example.capstoneproject.MODEL.Orders;
import com.example.capstoneproject.SERVICES.C_services;
import com.example.capstoneproject.SERVICES.CrudServices;
import com.example.capstoneproject.SERVICES.KafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/order")
public class Order_Controller {

    private static final Logger  log = LogManager.getLogger(Order_Controller.class.getName());

    @Autowired
    private KafkaProducer producer;
    @Autowired
    private CrudServices service;
    @Autowired
    private C_services cs;


    ////1)Adding order to data base
    @PostMapping("/add")
    @Operation(summary="API ADD SINGLE ORDER", description = "ID GENERATED AUTOMATICALLY")
    private String addOrder(@RequestBody   Order_DTO order_dto)
    {
        log.info("Inside the single order adding API");
        if(cs.check(order_dto))
        {
            throw new UserNotFoundException("Order Body Format Not Correct");
        }

        if(service.add_order(order_dto)) {
            log.debug("Single Order Successfully Added to the DataBase");
            return "Order Successfully added to data base";
        }
        else {
            log.debug("Order Failed Added to the DataBase");
            return "Order Failed adding to data base";
        }
    }




    ////2)Getting order by ID
    @GetMapping("/get/{id}")
    @Operation(summary="API  GET ORDER BY ID", description = "PASS A VALID(EXISTED) ID ")
    private Orders getOrderByID(@PathVariable("id") Integer id)
    {
        log.info("Inside the  GetByID Order API");
        return service.getorder_id(id);
    }


    //delete order by id
    @DeleteMapping("/delete/{id}")
    @Operation(summary="API DELETE ORDER BASED ON ID", description = "PASS A VALID(EXISTED) ID")
    private String deleteOrderById(@PathVariable("id")  Integer Id) {
        log.info("inside the DeleteById Order API");
        if (service.deleteOrder_id(Id)) {
            log.debug("Order with ID-->" + Id + "  Got deleted Successfully");
            return "order Successfully deleted";
        }
        else throw new UserNotFoundException("Order with id--> "+Id+" cannot be updated as it is not found");
    }






    ///////////////////////////////////////////////////////////////
    ///Batch-Adding Data using kafka
    @PostMapping("/add_batch")
    @Operation(summary="API  ADD ORDERS IN BATCH", description = "ORDER  ID GENERATED AUTOMATICALLY ")
    private String publish_orders(@RequestBody  List<Order_DTO> batch_order)
    {
        log.info("Inside the Batch processing of Adding  "+batch_order.size()+" Orders API");
        producer.sendObject(batch_order);
        log.debug("All "+batch_order.size()+"  Added Successfully to the DataBase");
        return " ALL Orders Successfully Added to DB";
    }




    //Batch-updating  Data using  kafka
    @PutMapping("/update_batch")
    @Operation(summary="API   UPDATE VALID(EXISTED) ORDERS IN BATCH", description = "PASS VALID(EXISTED) IDS")
    private String publish_update_orders(@RequestBody  List<Order_Update> batch_order)
    {
        log.info("Inside the Batch-update order request for "+batch_order.size()+" orders API");
        List<Order_Update>updated_orders =cs.check_batch(batch_order);
        if(updated_orders.size()==0)
            return "Order cannot be updated ID CANNOT found";
        List<Integer> ls = new ArrayList<Integer>();
        for(Order_Update or_up:updated_orders)
            ls.add(or_up.getOrder_id());
        producer.sendObject_update(updated_orders);
        log.debug("Total orders  "+updated_orders.size()+ "  have got updated successfully ");
        return "Only Orders with  ID----->"+ls+"   got Updated";
    }

}
