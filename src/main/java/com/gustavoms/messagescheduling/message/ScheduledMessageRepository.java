package com.gustavoms.messagescheduling.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledMessageRepository extends JpaRepository<ScheduledMessage, String> {
}
