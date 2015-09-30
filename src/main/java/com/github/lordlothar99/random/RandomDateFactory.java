/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link Date}.
 * 
 * @author Francois Lecomte
 */
public class RandomDateFactory implements Factory<Date> {

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
