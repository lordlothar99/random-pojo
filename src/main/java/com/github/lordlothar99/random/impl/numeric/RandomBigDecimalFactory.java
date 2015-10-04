/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigDecimal;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Factory;

/**
 * Generateur aleatoire de {@link BigDecimal}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigDecimalFactory implements Factory<BigDecimal> {

    /**
     * {@inheritDoc}
     */
    public BigDecimal create() {
        return new BigDecimal(String.valueOf(RandomUtils.nextInt(100) + RandomUtils.nextDouble()));
    }

}
