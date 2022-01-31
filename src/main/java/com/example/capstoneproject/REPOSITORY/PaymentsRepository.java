package com.example.capstoneproject.REPOSITORY;

import com.example.capstoneproject.MODEL.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments,Integer> {

}
