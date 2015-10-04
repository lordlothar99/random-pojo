/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

/**
 * Test for {@link RandomBigIntegerGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerGeneratorTest {

	@Test
	public void should_random_bigInteger_be_not_null() {
		RandomBigIntegerGenerator generator = new RandomBigIntegerGenerator();
		BigInteger result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_bigInteger_be_between_min_and_max() {
		BigInteger min = new BigInteger("-56856");
		BigInteger max = new BigInteger("1526575");
		RandomBigIntegerGenerator generator = new RandomBigIntegerGenerator();
		generator.setMin(min);
		generator.setMax(max);
		BigInteger randomBigInteger = generator.create();
		assertNotNull(randomBigInteger);
		assertThat("More than " + min, randomBigInteger, new GreaterOrEqual<BigInteger>(min));
		assertThat("Less than " + max, randomBigInteger, new LessOrEqual<BigInteger>(max));
	}

}
