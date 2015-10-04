/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Integer} generator
 * 
 * @author Francois Lecomte
 */
public class RandomIntegerGenerator implements Generator<Integer> {

    /**
     * Minimum
     */
    private int min;

    /**
     * Maximum
     */
    private int max;

    /**
     * Constructeur
     */
    public RandomIntegerGenerator() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Constructeur
     * 
     * @param max maximum
     */
    public RandomIntegerGenerator(int max) {
        this(0, max);
    }

    /**
     * Constructeur
     * 
     * @param min
     * @param max
     */
    public RandomIntegerGenerator(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    /**
     * {@inheritDoc}
     */
    public Integer create() {
        return min + RandomUtils.nextInt(max - min);
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }
}
