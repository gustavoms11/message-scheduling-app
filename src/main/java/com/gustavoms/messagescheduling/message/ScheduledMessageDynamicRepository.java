package com.gustavoms.messagescheduling.message;

import java.util.List;

public interface ScheduledMessageDynamicRepository {

    List<ScheduledMessage> search(ScheduledMessageSearchVO vo);
}
