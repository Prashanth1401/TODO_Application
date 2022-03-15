package com.todo.config;

import com.todo.rabbitmq.domain.ArchiveDTO;
import com.todo.service.ArchiveServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ArchiveServiceImpl service;

    @RabbitListener(queues = "impservice_queue")
    public void getCustomerFromRabbitMQ(ArchiveDTO archiveDTO){
        try {
            service.updateImpTask(archiveDTO.getUserId(), archiveDTO.getTaskId());
            System.out.println(archiveDTO.getUserId()+" "+archiveDTO.getTaskId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
