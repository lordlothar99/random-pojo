/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.api;

/**
 * @author Francois Lecomte
 */
public interface ObjectClassGenerator<T> extends Generator<T> {

    void setObjectClass(Class<? extends T> objectClass);
}
