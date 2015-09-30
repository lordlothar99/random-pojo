/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDate;

/**
 * Generateur aleatoire de {@link LocalDate}.
 * 
 * @author Francois Lecomte
 */
public class RandomLocalDateFactory implements Factory<LocalDate> {

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
