/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.api;

/**
 * @author Francois Lecomte
 * @param <T>
 */
public interface Generator<T> {

	T create();
}
