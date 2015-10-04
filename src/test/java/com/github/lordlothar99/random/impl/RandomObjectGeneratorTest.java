/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;

/**
 * @author Francois Lecomte
 *
 */
public class RandomObjectGeneratorTest {

	@Test
	public void testFoo() {
		RandomObjectGenerator<Foo> generator = new RandomObjectGenerator<Foo>(Foo.class);
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

}
