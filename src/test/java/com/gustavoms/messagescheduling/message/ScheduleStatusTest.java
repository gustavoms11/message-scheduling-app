package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.ScheduleStatusNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScheduleStatusTest {

    @Test
    public void whenFromStringThenReturnStatus() throws ScheduleStatusNotFoundException {
        var result = ScheduleStatus.fromString("SCHEDULED");

        assertThat(result).isEqualTo(ScheduleStatus.SCHEDULED);
    }

    @Test
    public void whenFromStringWithoutValidStringThenThrowException() {
        assertThatThrownBy(() -> ScheduleStatus.fromString("TESTE"))
                .isInstanceOf(ScheduleStatusNotFoundException.class);
    }
}
