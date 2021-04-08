package com.gustavoms.messagescheduling.converter;

import com.gustavoms.messagescheduling.exception.BadFormattedDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static LocalDateTime toDate(String isoDate) throws BadFormattedDateException {
        try {
            return LocalDateTime.parse(isoDate, formatter);
        } catch (DateTimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BadFormattedDateException(isoDate);
        }
    }

    public static String fromDate(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }
}
