package com.gustavoms.messagescheduling.converter;

import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateConverterTest {

    @Test
    public void whenConvertStringToDate() throws BadFormattedDateException {
        var date = "2021-01-01T14:30:22Z";
        var localDate = DateConverter.toDate(date);

        assertThat(localDate.toInstant(ZoneOffset.UTC).toEpochMilli()).isEqualTo(1609511422000L);
    }

    @Test
    public void whenConvertDateToString() {
        var instant = Instant.ofEpochMilli(1609511422000L);
        var localDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        var date = DateConverter.fromDate(localDate);

        assertThat(date).isEqualTo("2021-01-01T14:30:22Z");
    }

    @Test
    public void whenConvertStringToDateWithBadString() {
        var date = "TESTE";
        assertThatThrownBy(() -> DateConverter.toDate(date))
                .isInstanceOf(BadFormattedDateException.class);
    }
}
