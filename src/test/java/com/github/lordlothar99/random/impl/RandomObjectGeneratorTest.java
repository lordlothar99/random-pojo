/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.Foo2;
import com.github.lordlothar99.random.RandomGenerators;
import com.github.lordlothar99.random.api.RangedGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomFloatGenerator;

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
		assertNotNull("null objet", result);
		assertFalse("empty collection within generated object", result.getBars().isEmpty());
	}

	@Test
	public void should_set_not_be_filled_cause_already_initialized() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		Foo result = generator.create();
		assertNotNull("null objet", result);
		assertTrue("expected unfilled set", result.getQixs().isEmpty());
	}

	@Test
	public void should_set_be_filled_cause_already_initialized_but_forced() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		generator.setForcedFields("qixs");
		Foo result = generator.create();
		assertNotNull("null objet", result);
		assertFalse("expected filled set", result.getQixs().isEmpty());
	}

	@Test
	public void should_field_be_skipped_cause_already_initialized() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		Foo result = generator.create();
		assertNotNull("null objet", result);
		assertEquals('a', result.getaCharacter());
	}

	@Test
	public void should_field_be_generated_cause_already_initialized_but_forced() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		generator.setForcedFields("aCharacter");
		Foo result = generator.create();
		assertNotNull("null objet", result);
		assertNotEquals('_', result.getaCharacter());
	}

	@Test
	public void testRecursiveFoo() {
		RandomObjectGenerator<RecursiveFoo> generator = new RandomObjectGenerator<RecursiveFoo>(RecursiveFoo.class);
		RecursiveFoo result = generator.create();
		assertNotNull("null object", result);
		assertNotNull("null object", result.getParent());
	}

	@Test
	public void should_integer_be_between_bounds() {
		RandomObjectGenerator<Foo> generator = generators.objectGenerator(Foo.class);
		long min = -50L;
		long max = 0L;
		RangedGenerator<Long> longGenerator = generators.longGenerator(min, max);
		generator.setFieldGenerator("id", longGenerator);
		Foo result = generator.create();
		assertNotNull("null objet", result);
		assertFalse("empty collection within generated object", result.getBars().isEmpty());
		assertThat("More than " + min, result.getId(), new GreaterOrEqual<Long>(min));
		assertThat("Less than " + max, result.getId(), new LessOrEqual<Long>(max));

	}

	@Test
	public void should_several_generated_objects_be_different() {
		RandomObjectGenerator<Foo> generator = new RandomObjectGenerator<Foo>(Foo.class);
		Foo foo1 = generator.create();
		Foo foo2 = generator.create();
		assertNotNull("null objet", foo1);
		assertNotEquals("objects shouldn't be equal", foo1, foo2);
	}

	@Test
	public void should_generated_object_skipped_fields_be_uninitialized() {
		RandomObjectGenerator<Foo> generator = new RandomObjectGenerator<Foo>(Foo.class);
		generator.setSkippedFields("id");
		Foo foo = generator.create();
		assertNotNull("null objet", foo);
		assertEquals(0, foo.getId());
	}

	@Test
	public void should_generated_object_be_instantiated_with_constructor() {
		RandomObjectGenerator<Foo2> generator = new RandomObjectGenerator<Foo2>(Foo2.class);
		Foo2 foo = generator.create();
		assertNotNull("null objet", foo);
        assertNotEquals(0, foo.getId());
	}

    @Test
    public void should_primitive_fields_be_initialized_if_default() {
        RandomObjectGenerator<Foo> generator = new RandomObjectGenerator<Foo>(Foo.class);
        generator.setFieldGenerator("myFloat", new RandomFloatGenerator(1f, 5f));
        Foo foo = generator.create();
        assertNotNull("null objet", foo);
        assertNotEquals("should be initialized", 0f, foo.getMyFloat());
    }
}
