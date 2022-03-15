package com.todo.config;

import com.todo.model.User;
import com.todo.rabbotmq.domain.ImpServiceDTO;
import com.todo.service.ImpService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ImpService service;

    @RabbitListener(queues = "impservice_queue")
    public void getCustomerFromRabbitMQ(ImpServiceDTO impServiceDTO){
        try {
            service.updateImpTask(impServiceDTO.getUserId(), impServiceDTO.getTaskId());
            System.out.println(impServiceDTO.getUserId()+" "+impServiceDTO.getTaskId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
