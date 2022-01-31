package com.example.capstoneproject.REPOSITORY;

import com.example.capstoneproject.MODEL.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {
}
