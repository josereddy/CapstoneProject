package com.example.capstoneproject.MODEL;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@DynamicUpdate
@MappedSuperclass
@OptimisticLocking
public class Base_Order {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    public Date createdDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    public Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_payment_date")
    public Date order_payment_date;


    @PrePersist
    public void prePersist() {

        createdDate = new Date();
        order_payment_date = new Date();
        updatedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }
}
