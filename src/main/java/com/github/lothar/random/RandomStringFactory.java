/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Generateur aleatoire de {@link String}.
 * 
 * @author Francois Lecomte
 */
public class RandomStringFactory implements Factory<String> {

    /**
     * length
     */
    private int length;

    /**
     * Constructeur
     */
    public RandomStringFactory() {
        this(10);
    }

    /**
     * Constructeur
     * 
     * @param length
     */
    public RandomStringFactory(int length) {
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
