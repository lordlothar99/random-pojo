/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

/**
 * @author Francois Lecomte
 * @param <T>
 */
public interface Factory<T> extends org.apache.commons.collections.Factory {

    /**
     * {@inheritDoc}
     */
    T create();
}
