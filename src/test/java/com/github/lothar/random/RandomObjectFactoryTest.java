/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.junit.Assert;
import org.junit.Test;

import com.github.lothar.random.RandomObjectFactory;

/**
 * @author Francois Lecomte
 *
 */
public class RandomObjectFactoryTest {

	/**
	 * Test method for
	 * {@link fr.generali.maestro.framework.core.random.RandomCollectionFactory#create()}
	 * .
	 */
	@Test
	public void testFoo() {
		RandomObjectFactory<Foo> factory = new RandomObjectFactory<Foo>(Foo.class);
		Foo result = factory.create();
		Assert.assertNotNull("null objet", result);
		Assert.assertFalse("empty collection within generated object", result.getBars().isEmpty());
	}

	/**
	 * Test method for
	 * {@link fr.generali.maestro.framework.core.random.RandomCollectionFactory#create()}
	 * .
	 */
	@Test
	public void testRecursiveFoo() {
		RandomObjectFactory<RecursiveFoo> factory = new RandomObjectFactory<RecursiveFoo>(RecursiveFoo.class);
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
