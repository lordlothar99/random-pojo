/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.api;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * A {@link Generator} of container-like types : {@link Array},
 * {@link Collection}, {@link Map}...
 * 
 * @author Francois Lecomte
 * @param <T>
 *            generated object type
 */
public interface ContainerGenerator<T> extends Generator<T> {

	void setElementTypes(Class<?>... elementTypes);

	void setMinSize(int minSize);

	void setMaxSize(int minSize);
}
