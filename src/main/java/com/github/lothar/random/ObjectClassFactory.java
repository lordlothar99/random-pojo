/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

/**
 * @author Francois Lecomte
 */
public interface ObjectClassFactory<T> extends Factory<T> {

    void setObjectClass(Class<T> objectClass);
}
