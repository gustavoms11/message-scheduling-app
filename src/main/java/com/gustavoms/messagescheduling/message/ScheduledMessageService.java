package com.gustavoms.messagescheduling.message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScheduledMessageService {

    private ScheduledMessageRepository scheduledMessageRepository;

    public ScheduledMessageService(ScheduledMessageRepository scheduledMessageRepository) {
        this.scheduledMessageRepository = scheduledMessageRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScheduledMessage create(ScheduledMessage entity) {
        return entity;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<ScheduledMessage> search(ScheduledMessageSearchVO vo) {
        return new LinkedList<>();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ScheduledMessage find(String scheduledMessageId) {
        return new ScheduledMessage();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String scheduledMessageId) {

    }
}
