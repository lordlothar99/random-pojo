/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lordlothar99.random.RandomGeneratorsRegistry;
import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.RegistryAware;

/**
 * Abstract {@link Generator} with type of generated object
 * 
 * @author Francois Lecomte
 * @param <T>
 *            generated object type
 */
public abstract class AbstractGenerator<T> implements Generator<T>, RegistryAware {

	private Class<? extends T> objectClass;
	private RandomGeneratorsRegistry registry;
	private RandomToolkit toolkit;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractGenerator() {
		super();
	}

	public AbstractGenerator(Class<? extends T> clazz) {
		super();
		this.objectClass = clazz;
	}

	public Class<? extends T> getObjectClass() {
		return objectClass;
	}

	public RandomGeneratorsRegistry getRegistry() {
		if (registry == null) {
			registry = new RandomGeneratorsRegistry();
		}
		return registry;
	}

	public void setRegistry(RandomGeneratorsRegistry registry) {
		this.registry = registry;
		this.toolkit = null;
	}

	protected RandomToolkit toolkit() {
		if (toolkit == null) {
			toolkit = new RandomToolkit(getRegistry());
		}
		return toolkit;
	}

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
