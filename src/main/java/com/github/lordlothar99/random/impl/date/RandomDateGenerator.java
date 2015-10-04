/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Date} generator
 * 
 * @author Francois Lecomte
 */
public class RandomDateGenerator implements Generator<Date> {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
    public Date create() {
        final Date date = new Date();
        date.setDate(1 + RandomUtils.nextInt(365));
        date.setYear(1990 + RandomUtils.nextInt(151));
        return date;
    }

}
