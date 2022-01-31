package com.example.capstoneproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_Item_DTO {
    private String order_item_name;
    private String order_item_qty;
}
