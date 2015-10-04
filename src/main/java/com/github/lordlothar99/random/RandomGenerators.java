package com.github.lordlothar99.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang.ClassUtils;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.ObjectClassGenerator;
import com.github.lordlothar99.random.impl.RandomEnumGenerator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.RandomStringGenerator;
import com.github.lordlothar99.random.impl.collections.RandomArrayGenerator;
import com.github.lordlothar99.random.impl.collections.RandomCollectionGenerator;
import com.github.lordlothar99.random.impl.collections.RandomMapGenerator;
import com.github.lordlothar99.random.impl.date.RandomCalendarGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomXMLGregorianCalendarGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBigDecimalGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBigIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBooleanGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomByteGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomDoubleGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomFloatGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomLongGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomShortGenerator;

public class RandomGenerators {

	/**
	 * {@link RandomBigDecimalGenerator}
	 */
	public static final RandomBigDecimalGenerator BIG_DECIMAL = new RandomBigDecimalGenerator();
	/**
	 * {@link RandomBooleanGenerator}
	 */
	public static final RandomBooleanGenerator BOOLEAN = new RandomBooleanGenerator();
	/**
	 * {@link RandomCalendarGenerator}
	 */
	public static final RandomCalendarGenerator CALENDAR = new RandomCalendarGenerator();
	/**
	 * {@link RandomDateGenerator}
	 */
	public static final RandomDateGenerator DATE = new RandomDateGenerator();
	/**
	 * {@link RandomBigIntegerGenerator}
	 */
	public static final RandomBigIntegerGenerator BIG_INTEGER = new RandomBigIntegerGenerator();
	/**
	 * {@link RandomIntegerGenerator}
	 */
	public static final RandomIntegerGenerator INTEGER = new RandomIntegerGenerator();
	/**
	 * {@link RandomLongGenerator}
	 */
	public static final RandomLongGenerator LONG = new RandomLongGenerator();
	/**
	 * {@link RandomShortGenerator}
	 */
	public static final RandomShortGenerator SHORT = new RandomShortGenerator();
	/**
	 * {@link RandomByteGenerator}
	 */
	public static final RandomByteGenerator BYTE = new RandomByteGenerator();
	/**
	 * {@link RandomFloatGenerator}
	 */
	public static final RandomFloatGenerator FLOAT = new RandomFloatGenerator();
	/**
	 * {@link RandomDoubleGenerator}
	 */
	public static final RandomDoubleGenerator DOUBLE = new RandomDoubleGenerator();
	/**
	 * {@link RandomStringGenerator}
	 */
	public static final RandomStringGenerator STRING = new RandomStringGenerator();
	/**
	 * {@link RandomLocalDateGenerator}
	 */
	public static final RandomLocalDateGenerator LOCAL_DATE = new RandomLocalDateGenerator();
	/**
	 * {@link RandomLocalDateTimeGenerator}
	 */
	public static final RandomLocalDateTimeGenerator LOCAL_DATE_TIME = new RandomLocalDateTimeGenerator();
	/**
	 * {@link RandomDateTimeGenerator}
	 */
	public static final RandomDateTimeGenerator DATE_TIME = new RandomDateTimeGenerator();
	/**
	 * {@link RandomXMLGregorianCalendarGenerator}
	 */
	public static final RandomXMLGregorianCalendarGenerator XMLGREGORIANCALENDAR = new RandomXMLGregorianCalendarGenerator();
	private Map<Class<?>, Object> registry = new HashMap<Class<?>, Object>();

	public RandomGenerators() {
		registry.put(BigDecimal.class, RandomGenerators.BIG_DECIMAL);
		registry.put(Boolean.class, RandomGenerators.BOOLEAN);
		registry.put(boolean.class, RandomGenerators.BOOLEAN);
		registry.put(Calendar.class, RandomGenerators.CALENDAR);
		registry.put(Date.class, RandomGenerators.DATE);
		registry.put(BigInteger.class, RandomGenerators.BIG_INTEGER);
		registry.put(Integer.class, RandomGenerators.INTEGER);
		registry.put(int.class, RandomGenerators.INTEGER);
		registry.put(Long.class, RandomGenerators.LONG);
		registry.put(long.class, RandomGenerators.LONG);
		registry.put(Short.class, RandomGenerators.SHORT);
		registry.put(short.class, RandomGenerators.SHORT);
		registry.put(Byte.class, RandomGenerators.BYTE);
		registry.put(byte.class, RandomGenerators.BYTE);
		registry.put(Double.class, RandomGenerators.DOUBLE);
		registry.put(double.class, RandomGenerators.DOUBLE);
		registry.put(Float.class, RandomGenerators.FLOAT);
		registry.put(float.class, RandomGenerators.FLOAT);
		registry.put(String.class, RandomGenerators.STRING);
		try {
			registry.put(Class.forName("org.joda.time.LocalDate"), RandomGenerators.LOCAL_DATE);
			registry.put(Class.forName("org.joda.time.LocalDateTime"), RandomGenerators.LOCAL_DATE_TIME);
			registry.put(Class.forName("org.joda.time.DateTime"), RandomGenerators.DATE_TIME);
		} catch (ClassNotFoundException e) {
			// joda unavailable
		}
		registry.put(XMLGregorianCalendar.class, RandomGenerators.XMLGREGORIANCALENDAR);

		registry.put(Enum.class, RandomEnumGenerator.class);
		registry.put(Collection.class, RandomCollectionGenerator.class);
		registry.put(Map.class, RandomMapGenerator.class);
		registry.put(Object.class, RandomObjectGenerator.class);
	}

	public Object get(Class<?> theClass) {
		return registry.get(theClass);
	}

	public void put(Class<?> type, Generator<?> generator) {
		registry.put(type, generator);
	}

	public void put(Class<?> type, Class<?> generatorClass) {
		registry.put(type, generatorClass);
	}

	/**
	 * @param <O>
	 *            Type de la donnee e generer
	 * @param type
	 *            Type de la donnee e generer
	 * @return un {@link Generator} pour le type de donnees specifie
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <O> Generator<O> getInstance(Class<O> type) {

		Generator<O> generator = null;

		if (Enum.class.isAssignableFrom(type)) {
			generator = new RandomEnumGenerator(type);
		} else if (type.isArray()) {
			generator = new RandomArrayGenerator(type);
			// } else if (Collection.class.isAssignableFrom(type)) {
			// factory = new RandomCollectionGenerator(type);
			// } else if (Map.class.isAssignableFrom(type)) {
			// factory = new RandomMapGenerator(type);
			// } else {
			// factory = new RandomObjectGenerator<O>(type);
		}

		// exploration par les superclass
		for (Class<?> theClass = type; generator == null && theClass != null; theClass = theClass.getSuperclass()) {
			generator = getFromRegistry(generator, theClass);
		}

		if (generator instanceof ObjectClassGenerator) {
			ObjectClassGenerator objectClassGenerator = (ObjectClassGenerator) generator;
			objectClassGenerator.setObjectClass(type);
		}
		return generator;
	}

	/**
	 * @param factory
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <O> Generator<O> getFromRegistry(Generator<O> factory, Class<?> type) {
		int i = 0;
		List<Class<?>> interfaces = ClassUtils.getAllInterfaces(type);
		// exploration par les interfaces
		Class<?> theClass = type;
		do {
			Object registeredObject = registry.get(theClass);
			if (registeredObject instanceof Generator) {
				factory = (Generator<O>) registeredObject;
			} else if (registeredObject != null) {
				Class<O> factoryClass = (Class<O>) registeredObject;
				try {
					try {
						factory = (Generator<O>) ConstructorUtils.invokeConstructor(factoryClass, type);
					} catch (NoSuchMethodException e) {
						factory = (Generator<O>) ConstructorUtils.invokeConstructor(factoryClass, null);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			if (factory == null && theClass != null && i < interfaces.size()) {
				theClass = interfaces.get(i);
				i++;
			}
		} while (factory == null && theClass != null && i < interfaces.size());
		return factory;
	}

}
