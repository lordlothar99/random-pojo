/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.api;

/**
 * @author Francois Lecomte
 */
public interface ObjectClassFactory<T> extends Factory<T> {

    void setObjectClass(Class<T> objectClass);
}
