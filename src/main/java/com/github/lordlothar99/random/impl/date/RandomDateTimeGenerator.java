/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;

import com.github.lordlothar99.random.api.Generator;

/**
 * Generateur aleatoire de {@link DateTime}.
 * 
 * @author Francois Lecomte
 */
public class RandomDateTimeGenerator implements Generator<DateTime> {

    /**
     * {@inheritDoc}
     */
    public DateTime create() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfYear(1 + RandomUtils.nextInt(365));
        dateTime = dateTime.withYear(1990 + RandomUtils.nextInt(151));
        final int millis = RandomUtils.nextInt(23 * 60 * 60 * 1000);
        dateTime = dateTime.withMillisOfDay(millis);
        return dateTime;
    }
}
