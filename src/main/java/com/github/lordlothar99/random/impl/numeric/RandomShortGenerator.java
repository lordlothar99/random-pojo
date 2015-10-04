/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Generateur aleatoire de {@link Short}.
 * 
 * @author Francois Lecomte
 */
public class RandomShortGenerator implements Generator<Short> {

    /**
     * Minimum
     */
    private short min;

    /**
     * Maximum
     */
    private short max;

    /**
     * Constructeur
     */
    public RandomShortGenerator() {
        this(Short.MAX_VALUE);
    }

    /**
     * Constructeur
     * 
     * @param max
     */
    public RandomShortGenerator(short max) {
        this((short ) 0, max);
    }

    /**
     * Constructeur
     * 
     * @param min
     * @param max
     */
    public RandomShortGenerator(short min, short max) {
        super();
        this.min = min;
        this.max = max;
    }

    /**
     * {@inheritDoc}
     */
    public Short create() {
        return (short ) (min + (short ) RandomUtils.nextInt(max - min));
    }

    /**
     * @param max the max to set
     */
    public void setMax(short max) {
        this.max = max;
    }

    /**
     * @param min the min to set
     */
    public void setMin(short min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public short getMax() {
        return max;
    }

    /**
     * @return the min
     */
    public short getMin() {
        return min;
    }
}