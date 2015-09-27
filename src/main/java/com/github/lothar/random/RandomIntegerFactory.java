/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link Integer}.
 * 
 * @author Francois Lecomte
 */
public class RandomIntegerFactory implements Factory<Integer> {

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
    public RandomIntegerFactory() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Constructeur
     * 
     * @param max maximum
     */
    public RandomIntegerFactory(int max) {
        this(0, max);
    }

    /**
     * Constructeur
     * 
     * @param min
     * @param max
     */
    public RandomIntegerFactory(int min, int max) {
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
