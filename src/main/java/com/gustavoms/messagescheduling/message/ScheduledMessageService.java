package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScheduledMessageService {

    private final ScheduledMessageRepository scheduledMessageRepository;
    private final ScheduledMessageValidator scheduledMessageValidator;
    private final ScheduledMessageEnhancer scheduledMessageEnhancer;

    public ScheduledMessageService(
            ScheduledMessageRepository scheduledMessageRepository,
            ScheduledMessageValidator scheduledMessageValidator,
            ScheduledMessageEnhancer scheduledMessageEnhancer) {
        this.scheduledMessageRepository = scheduledMessageRepository;
        this.scheduledMessageValidator = scheduledMessageValidator;
        this.scheduledMessageEnhancer = scheduledMessageEnhancer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScheduledMessage create(ScheduledMessage entity) throws BaseException {
        scheduledMessageValidator.validateEntityPassedToPersist(entity);
        var entityToPersist = scheduledMessageEnhancer.enhanceToCreate(entity);
        var persistedEntity = scheduledMessageRepository.save(entityToPersist);
        return persistedEntity;
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
