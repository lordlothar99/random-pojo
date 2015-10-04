/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDate;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link LocalDate} generator
 * 
 * @author Francois Lecomte
 */
public class RandomLocalDateGenerator implements Generator<LocalDate> {

    /**
     * {@inheritDoc}
     */
    public LocalDate create() {
        LocalDate localDate = new LocalDate();
        localDate = localDate.withDayOfYear(1 + RandomUtils.nextInt(365));
        localDate = localDate.withYear(1990 + RandomUtils.nextInt(151));
        return localDate;
    }

}
