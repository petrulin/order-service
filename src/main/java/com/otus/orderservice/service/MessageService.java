package com.otus.orderservice.service;

import com.otus.orderservice.entity.Message;


public interface MessageService {

    Message save(Message user);
    Message findById(String msgId);

}
