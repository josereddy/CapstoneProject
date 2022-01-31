package com.example.capstoneproject.SERVICES;

import com.example.capstoneproject.DTO.Order_DTO;
import com.example.capstoneproject.DTO.Order_Update;
import com.example.capstoneproject.Exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger log = LogManager.getLogger(KafkaConsumer.class.getName());
    @Autowired
    private CrudServices service;

    @Qualifier("jsonKafkaListenerContainerFactory")
    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",topics = "${kafka.topic.json-demo.name1}", groupId = "${kafka.topic.json-demo.groupId1}")
    public void getMessage(Order_DTO order_dto) {

        log.info("Inside the kafka Listener ADDING ORDER");
        service.add_order(order_dto);
        log.info("All objects got successfully  Transmitted to Database");
    }


    @Qualifier("jsonKafkaListenerContainerFactory_update")
    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory_update",topics = "${kafka.topic.json-demo.name2}", groupId = "${kafka.topic.json-demo.groupId2}")
    public void getMessage_Update(Order_Update order) {

        log.info("Inside the kafka Listener UPDATING ORDER");
        service.updateorder_status(order);
        log.info("All objects got successfully  Transmitted to Database");
    }
}
