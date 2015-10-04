/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.ObjectClassGenerator;

/**
 * Abstract {@link Generator} with type of generated object
 * 
 * @author Francois Lecomte
 * @param <T>
 *            generated object type
 */
public abstract class AbstractGenerator<T> implements ObjectClassGenerator<T> {

	/**
	 * {@link Class} de l'objet genere
	 */
	private Class<T> objectClass;

	public AbstractGenerator() {
		super();
	}

	public AbstractGenerator(Class<T> clazz) {
		super();
		this.objectClass = clazz;
	}

	public Class<T> getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(Class<T> objectClass) {
		this.objectClass = objectClass;
	}
}
