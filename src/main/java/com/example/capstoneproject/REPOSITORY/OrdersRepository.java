package com.example.capstoneproject.REPOSITORY;

import com.example.capstoneproject.DTO.Order_DTO;
import com.example.capstoneproject.MODEL.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {


}
