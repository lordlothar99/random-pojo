package com.github.lordlothar99.random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.collections.RandomArrayGenerator;
import com.github.lordlothar99.random.impl.collections.RandomCollectionGenerator;
import com.github.lordlothar99.random.impl.element.RandomEnumGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;
import com.github.lordlothar99.random.impl.string.RandomStringGenerator;

@SuppressWarnings("unchecked")
public class RandomGeneratorsRegistryTest {

	private RandomGeneratorsRegistry registry = new RandomGeneratorsRegistry();

	private <T, G extends Generator<T>> void assertGeneratorOk(Class<T> type, Class<G> generatorClass) {
		Generator<T> generator = registry.getGenerator(type);
		assertNotNull("generator not found for type '" + type.getName() + "'", generator);
		assertTrue("generator is not an instance of '" + generatorClass.getName() + "' : "
				+ generator.getClass().getName(), generatorClass.isInstance(generator));
	}

	@Test
	public void should_return_integer_generator() {
		assertGeneratorOk(Integer.class, RandomIntegerGenerator.class);
	}

	@Test
	public void should_return_int_generator() {
		assertGeneratorOk(int.class, RandomIntegerGenerator.class);
	}

	@Test
	public void should_return_string_generator() {
		assertGeneratorOk(String.class, RandomStringGenerator.class);
	}

	@Test
	public void should_return_foo_generator() {
		assertGeneratorOk(Foo.class, RandomObjectGenerator.class);
	}

	@Test
	public void should_return_list_generator() {
		assertGeneratorOk(List.class, RandomCollectionGenerator.class);
	}

	@Test
	public void should_return_enum_generator() {
		assertGeneratorOk(MyEnum.class, RandomEnumGenerator.class);
	}

	@Test
	public void should_return_array_generator() {
		assertGeneratorOk(int[].class, RandomArrayGenerator.class);
	}

	@Test
	public void should_return_array_of_objects_generator() {
		assertGeneratorOk(Integer[].class, RandomArrayGenerator.class);
	}
}
