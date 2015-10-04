/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.api.ContainerGenerator;

/**
 * Abstract {@link ContainerGenerator}.
 * 
 * @author Francois Lecomte
 * @param <T>
 *            generated object type
 */
public abstract class AbstractContainerGenerator<T> extends AbstractGenerator<T>implements ContainerGenerator<T> {

	private Class<?>[] elementTypes;
	private int minSize = 2;
	private int maxSize = 10;
	private RandomToolkit toolkit = new RandomToolkit();
	private int maxGenerationRetryCount = 10;
	private boolean errorOnNotEnoughDifferentElements = false;

	public AbstractContainerGenerator(Class<T> clazz) {
		super(clazz);
	}

	public AbstractContainerGenerator(Class<T> clazz, Class<?>... elementTypes) {
		super(clazz);
		this.elementTypes = elementTypes;
	}

	public Class<?>[] getElementTypes() {
		return elementTypes;
	}

	public void setElementTypes(Class<?>... elementTypes) {
		this.elementTypes = elementTypes;
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

	public T create() {
		int size = generateSize();
		T container = newContainer(size);
		for (int i = 0; i < size; i++) {
			appendWithRetry(container, i);
		}
		return container;
	}

	protected void appendWithRetry(T container, int index) {
		int retryCount = 0;
		boolean success;
		do {
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

	protected abstract boolean append(T container, int elementIndex);

	protected abstract T newContainer(int size);

	protected abstract int size(T container);

	protected int generateSize() {
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
