package com.example.capstoneproject.MODEL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Item_id")
    private Integer item_id;
    private String order_item_name;
    private String order_item_qty;
}
