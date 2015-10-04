/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static org.apache.commons.lang.ArrayUtils.getLength;
import static org.apache.commons.lang.ArrayUtils.isEmpty;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;

/**
 * Abstract {@link ContainerGenerator}.
 * 
 * @author Francois Lecomte
 * @param <T>
 *            generated object type
 */
public abstract class AbstractContainerGenerator<T> extends AbstractGenerator<T>implements ContainerGenerator<T> {

	private Class<?>[] elementsTypes;
	private Generator<?>[] elementsGenerators;
	private int minSize = 2;
	private int maxSize = 10;
	// TODO
	private RandomToolkit toolkit = new RandomToolkit();
	private int maxGenerationRetryCount = 10;
	private boolean errorOnNotEnoughDifferentElements = false;

	public AbstractContainerGenerator(Class<? extends T> clazz) {
		super(clazz);
	}

	public AbstractContainerGenerator(Class<? extends T> clazz, Class<?>... elementTypes) {
		super(clazz);
		this.elementsTypes = elementTypes;
	}

	public AbstractContainerGenerator(Class<? extends T> clazz, Generator<?>... elementsGenerators) {
		super(clazz);
		this.elementsGenerators = elementsGenerators;
	}

	public Class<?>[] getElementsTypes() {
		return elementsTypes;
	}

	public void setElementsTypes(Class<?>... elementTypes) {
		this.elementsTypes = elementTypes;
	}

	public void setElementsGenerators(Generator<?>... elementsGenerators) {
		this.elementsGenerators = elementsGenerators;
	}

	public Generator<?>[] getElementsGenerators() {
		return elementsGenerators;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setSize(int size) {
		this.setMinSize(size);
		this.setMaxSize(size);
	}

	public void setMaxGenerationRetryCount(int maxGenerationRetryCount) {
		this.maxGenerationRetryCount = maxGenerationRetryCount;
	}

	public int getMaxGenerationRetryCount() {
		return maxGenerationRetryCount;
	}

	public T create() {
		logger.debug("Generating a container...");
		int size = generateSize();

		logger.debug("Instantiating a new container for {} elements", size);
		T container = newContainer(size);
		for (int i = 0; i < size; i++) {
			appendWithRetry(container, i);
		}
		logger.debug("Container generated : {}", container);
		return container;
	}

	protected void appendWithRetry(T container, int index) {
		int retryCount = 0;
		boolean success = true;
		do {
			if (!success) {
				logger.debug("Retrying...");
			}
			success = append(container, index);
			retryCount++;
		} while (!success && retryCount < maxGenerationRetryCount);
		if (!success) {
			generationFailure("Unable to generate enough different elements to match required size");
		}
	}

	private void generationFailure(String message) {
		if (errorOnNotEnoughDifferentElements) {
			throw new RuntimeException(message);
		} else {
			logger.warn(message);
		}
	}

	protected boolean append(T container, int elementIndex) {
		logger.debug("Appending a element at position {} : ", elementIndex);
		Generator<?> generator = elementGenerator(0);
		logger.debug("Using element generator : {}", generator);
		Object element = generator.create();
		logger.debug("Generated element is : {}", element);
		boolean success = append(container, elementIndex, element);
		logger.debug("Append status : {}", success);
		return success;
	}

	protected Generator<?> elementGenerator(int index) {
		Generator<?> generator = null;
		logger.debug("Looking for generator for elements of index " + index + "..");
		if (getLength(elementsGenerators) > index) {
			generator = elementsGenerators[index];
			logger.debug("Generator found within : {}", generator);
		}
		if (generator == null && getLength(elementsTypes) > index) {
			Class<?> elementType = elementsTypes[index];
			generator = toolkit.getRegistry().getGenerator(elementType);
			logger.debug("Generator found in registry : {}", generator);
		}
		return generator;
	}

	protected abstract boolean append(T container, int elementIndex, Object element);

	protected abstract T newContainer(int size);

	protected abstract int size(T container);

	protected int generateSize() {
		if (isEmpty(elementsGenerators) && isEmpty(elementsTypes)) {
			logger.error("Unable to generate elements when neither elementsGenerators nor elementsTypes are specified");
			return 0;
		}
		if (minSize == maxSize) {
			return minSize;
		}
		int size = toolkit.integer(minSize, maxSize);
		return size;
	}

	public void setErrorOnNotEnoughDifferentElements(boolean errorOnNotEnoughDifferentElements) {
		this.errorOnNotEnoughDifferentElements = errorOnNotEnoughDifferentElements;
	}

	public boolean isErrorOnNotEnoughDifferentElements() {
		return errorOnNotEnoughDifferentElements;
	}

	public RandomToolkit getToolkit() {
		return toolkit;
	}

	public void setToolkit(RandomToolkit toolkit) {
		this.toolkit = toolkit;
	}
}
