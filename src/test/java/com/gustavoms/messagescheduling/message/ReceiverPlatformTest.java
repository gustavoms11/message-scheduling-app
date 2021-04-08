package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.exception.ReceiverPlatformNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReceiverPlatformTest {

    @Test
    public void whenFromStringThenReturnReceiverPlatform()
            throws ReceiverPlatformNotFoundException {
        var result = ReceiverPlatform.fromString("SMS");

        assertThat(result).isEqualTo(ReceiverPlatform.SMS);
    }

    @Test
    public void whenFromStringSetThenReturnReceiverPlatformSet()
            throws ReceiverPlatformNotFoundException {
        var result = ReceiverPlatform.fromStringSet(Set.of("PUSH","WHATSAPP"));

        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(ReceiverPlatform.PUSH, ReceiverPlatform.WHATSAPP);
    }

    @Test
    public void whenFromStringWithoutValidStringThenThrowException() {
        assertThatThrownBy(() -> ReceiverPlatform.fromString("TESTE"))
                .isInstanceOf(ReceiverPlatformNotFoundException.class);
    }
}
