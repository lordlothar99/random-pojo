/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.apache.commons.lang.RandomStringUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link String} generator
 * 
 * @author Francois Lecomte
 */
public class RandomStringGenerator implements Generator<String> {

    /**
     * length
     */
    private int length;

    /**
     * Constructeur
     */
    public RandomStringGenerator() {
        this(10);
    }

    /**
     * Constructeur
     * 
     * @param length
     */
    public RandomStringGenerator(int length) {
        super();
        this.length = length;
    }

    /**
     * {@inheritDoc}
     */
    public String create() {
        return RandomStringUtils.random(length, true, true);
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }
}
