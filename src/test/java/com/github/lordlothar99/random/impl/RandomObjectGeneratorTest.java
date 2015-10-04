/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;

/**
 * @author Francois Lecomte
 *
 */
public class RandomObjectGeneratorTest {

	/**
	 * Test method for
	 * {@link fr.generali.maestro.framework.core.random.RandomCollectionGenerator#create()}
	 * .
	 */
	@Test
	public void testFoo() {
		RandomObjectGenerator<Foo> factory = new RandomObjectGenerator<Foo>(Foo.class);
		Foo result = factory.create();
		Assert.assertNotNull("null objet", result);
		Assert.assertFalse("empty collection within generated object", result.getBars().isEmpty());
	}

	/**
	 * Test method for
	 * {@link fr.generali.maestro.framework.core.random.RandomCollectionGenerator#create()}
	 * .
	 */
	@Test
	public void testRecursiveFoo() {
		RandomObjectGenerator<RecursiveFoo> factory = new RandomObjectGenerator<RecursiveFoo>(RecursiveFoo.class);
		RecursiveFoo result = factory.create();
		Assert.assertNotNull("null objet", result);
	}

	public static class RecursiveFoo {
		private RecursiveFoo2 parent;
		private String stringProperty;

		/**
		 * @return the parent
		 */
		public RecursiveFoo2 getParent() {
			return parent;
		}

		/**
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(RecursiveFoo2 parent) {
			this.parent = parent;
		}

		public String getStringProperty() {
			return stringProperty;
		}

		public void setStringProperty(String stringProperty) {
			this.stringProperty = stringProperty;
		}

	}

	public static class RecursiveFoo2 {
		private RecursiveFoo parent;

		/**
		 * @return the parent
		 */
		public RecursiveFoo getParent() {
			return parent;
		}

		/**
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(RecursiveFoo parent) {
			this.parent = parent;
		}
	}

}
