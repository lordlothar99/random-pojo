/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Calendar} generator
 * 
 * @author Francois Lecomte
 */
public class RandomCalendarGenerator implements Generator<Calendar> {

    /**
     * {@inheritDoc}
     */
    public Calendar create() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1 + RandomUtils.nextInt(365));
        calendar.set(Calendar.YEAR, 1990 + RandomUtils.nextInt(51));
        return calendar;
    }

}
