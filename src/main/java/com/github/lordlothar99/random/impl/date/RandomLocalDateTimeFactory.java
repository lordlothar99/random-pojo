/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDateTime;

import com.github.lordlothar99.random.api.Factory;

/**
 * Generateur aleatoire de {@link LocalDateTime}.
 * 
 * @author Francois Lecomte
 */
public class RandomLocalDateTimeFactory implements Factory<LocalDateTime> {

    /**
     * {@inheritDoc}
     */
    public LocalDateTime create() {
        LocalDateTime localDateTime = new LocalDateTime();
        localDateTime = localDateTime.withDayOfYear(1 + RandomUtils.nextInt(365));
        localDateTime = localDateTime.withYear(1990 + RandomUtils.nextInt(151));
        final int millis = RandomUtils.nextInt(23 * 60 * 60 * 1000);
        localDateTime = localDateTime.withMillisOfDay(millis);
        return localDateTime;
    }
}
