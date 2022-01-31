package com.example.capstoneproject.SERVICES;

import com.example.capstoneproject.DTO.Order_DTO;
import com.example.capstoneproject.DTO.Order_Update;
import com.example.capstoneproject.Exception.UserNotFoundException;
import com.example.capstoneproject.MODEL.Orders;
import com.example.capstoneproject.REPOSITORY.OrdersRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudServices {


    private static final Logger log = LogManager.getLogger(CrudServices.class.getName());

    @Autowired
    private C_services cs;
    @Autowired
    private OrdersRepository order_repo;
    private Orders orders;




    public Boolean add_order(Order_DTO order_dto) {


            log.info("Inside CRUD SERVICE Order Adding  to Database");
            orders = cs.Dto_Entity(order_dto);
            log.info("Inside SERVICE order dto to entity mapped successfully");
            order_repo.save(orders);
            log.info("Inside Service Order adding successfully added to DB");
            return true;
    }



    public Orders getorder_id(Integer id) {

        log.info("Inside CRUD Service to get the Order Object by id from Database");
        Optional<Orders> order_op = order_repo.findById(id);
        orders = null;
        if(order_op.isPresent()) {
            orders = order_op.get();
        }
        if(orders== null)
            throw new UserNotFoundException("There is no order found with given id---->"+id);
        return orders;
    }

    public Boolean deleteOrder_id(Integer id) {
        log.info("Inside CRUD Service to Delete the Order Object by id in Database");
        Optional<Orders> order = order_repo.findById(id);
        if(!(order.isPresent()))
            throw new UserNotFoundException("Order with id--> "+id+" cannot be deleted");
        order_repo.deleteById(id);
        return true;
    }


    public void  updateorder_status(Order_Update order_up) {
        log.info("Inside Crud Service trying  update the Order Object to Database");
        order_repo.findById(order_up.getOrder_id()).map(order_sa->{
                    order_sa.setOrder_status(order_up.getOrder_status());
                    return order_repo.save(order_sa);});
        }
    }


