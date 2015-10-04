/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.RandomGenerators;
import com.github.lordlothar99.random.api.RangedGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomLongGenerator;

/**
 * @author Francois Lecomte
 *
 */
public class RandomObjectGeneratorTest {

	private RandomGenerators generators = new RandomGenerators();

	@Test
	public void should_list_contain_several_elements() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		Foo result = generator.create();
		Assert.assertNotNull("null objet", result);
		Assert.assertFalse("empty collection within generated object", result.getBars().isEmpty());
	}

	@Test
	public void testRecursiveFoo() {
		RandomObjectGenerator<RecursiveFoo> generator = new RandomObjectGenerator<RecursiveFoo>(RecursiveFoo.class);
		RecursiveFoo result = generator.create();
		Assert.assertNotNull("null objet", result);
	}

	@Test
	public void should_integer_be_between_bounds() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		long min = -50L;
		long max = 0L;
		RangedGenerator<Long> longGenerator = generators.longGenerator(min, max);
		generator.setFieldGenerator("id", longGenerator);
		Foo result = generator.create();
		Assert.assertNotNull("null objet", result);
		Assert.assertFalse("empty collection within generated object", result.getBars().isEmpty());
		assertThat("More than " + min, result.getId(), new GreaterOrEqual<Long>(min));
		assertThat("Less than " + max, result.getId(), new LessOrEqual<Long>(max));

	}

}
