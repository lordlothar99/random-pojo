/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

import com.github.lordlothar99.random.RandomGeneratorsRegistry;

/**
 * Test for {@link RandomBigIntegerGenerator}.
 * 
 * @author Francois Lecomte
 */
public abstract class AbstractRandomNumericGeneratorTest<T extends Comparable<T>, G extends AbstractRandomNumericGenerator<T>> {

	@Test
	public void should_random_numeric_be_not_null() {
		G generator = newGenerator();
		T result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_numeric_be_between_min_and_max() {
		T min = min();
		T max = max();
		G generator = newGenerator();
		generator.setMin(min);
		generator.setMax(max);
		T randomValue = generator.create();
		assertNotNull(randomValue);
		assertThat("More than " + min, randomValue, new GreaterOrEqual<T>(min));
		assertThat("Less than " + max, randomValue, new LessOrEqual<T>(max));
	}

	protected G newGenerator();

	protected abstract T min();

	protected abstract T max();

}
