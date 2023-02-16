package com.otus.orderservice.service.impl;

import com.otus.orderservice.entity.Message;
import com.otus.orderservice.repository.MessageRepository;
import com.otus.orderservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("currencyService")
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findById(String msgId) {
        return messageRepository.findById(msgId).orElse(null);
    }

}
