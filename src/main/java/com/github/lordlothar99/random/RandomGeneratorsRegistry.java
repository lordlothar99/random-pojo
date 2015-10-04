package com.github.lordlothar99.random;

import static org.apache.commons.beanutils.ConstructorUtils.invokeConstructor;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomGeneratorsRegistry {

	public static final RandomBigDecimalGenerator BIG_DECIMAL = new RandomBigDecimalGenerator();
	public static final RandomBooleanGenerator BOOLEAN = new RandomBooleanGenerator();
	public static final RandomCalendarGenerator CALENDAR = new RandomCalendarGenerator();
	public static final RandomDateGenerator DATE = new RandomDateGenerator();
	public static final RandomBigIntegerGenerator BIG_INTEGER = new RandomBigIntegerGenerator();
	public static final RandomIntegerGenerator INTEGER = new RandomIntegerGenerator();
	public static final RandomLongGenerator LONG = new RandomLongGenerator();
	public static final RandomShortGenerator SHORT = new RandomShortGenerator();
	public static final RandomByteGenerator BYTE = new RandomByteGenerator();
	public static final RandomFloatGenerator FLOAT = new RandomFloatGenerator();
	public static final RandomDoubleGenerator DOUBLE = new RandomDoubleGenerator();
	public static final RandomStringGenerator STRING = new RandomStringGenerator();
	public static final RandomLocalDateGenerator LOCAL_DATE = new RandomLocalDateGenerator();
	public static final RandomLocalDateTimeGenerator LOCAL_DATE_TIME = new RandomLocalDateTimeGenerator();
	public static final RandomDateTimeGenerator DATE_TIME = new RandomDateTimeGenerator();
	public static final RandomXMLGregorianCalendarGenerator XMLGREGORIANCALENDAR = new RandomXMLGregorianCalendarGenerator();

	private Map<Class<?>, Object> registry = new HashMap<Class<?>, Object>();

	public RandomGeneratorsRegistry() {
		registry.put(BigDecimal.class, BIG_DECIMAL);
		registry.put(Boolean.class, BOOLEAN);
		registry.put(boolean.class, BOOLEAN);
		registry.put(Calendar.class, CALENDAR);
		registry.put(Date.class, DATE);
		registry.put(BigInteger.class, BIG_INTEGER);
		registry.put(Integer.class, INTEGER);
		registry.put(int.class, INTEGER);
		registry.put(Long.class, LONG);
		registry.put(long.class, LONG);
		registry.put(Short.class, SHORT);
		registry.put(short.class, SHORT);
		registry.put(Byte.class, BYTE);
		registry.put(byte.class, BYTE);
		registry.put(Double.class, DOUBLE);
		registry.put(double.class, DOUBLE);
		registry.put(Float.class, FLOAT);
		registry.put(float.class, FLOAT);
		registry.put(String.class, STRING);
		try {
			registry.put(Class.forName("org.joda.time.LocalDate"), LOCAL_DATE);
			registry.put(Class.forName("org.joda.time.LocalDateTime"), LOCAL_DATE_TIME);
			registry.put(Class.forName("org.joda.time.DateTime"), DATE_TIME);
		} catch (ClassNotFoundException e) {
			// joda unavailable
		}
		registry.put(XMLGregorianCalendar.class, XMLGREGORIANCALENDAR);

		registry.put(Enum.class, RandomEnumGenerator.class);
		registry.put(Collection.class, RandomCollectionGenerator.class);
		registry.put(Map.class, RandomMapGenerator.class);
		registry.put(Array.class, RandomArrayGenerator.class);
		registry.put(Object.class, RandomObjectGenerator.class);
	}

	public void put(Class<?> type, Generator<?> generator) {
		registry.put(type, generator);
	}

	public void put(Class<?> type, Class<?> generatorClass) {
		registry.put(type, generatorClass);
	}

	public <T> Generator<T> getGenerator(Class<T> type) {

		Generator<T> generator = null;

		if (type.isArray()) {
			generator = new RandomArrayGenerator(type);
		}

		// exploration par les superclass
		for (Class<?> theClass = type; generator == null && theClass != null; theClass = theClass.getSuperclass()) {
			generator = lookupGenerator(theClass);
		}

		if (generator instanceof ObjectClassGenerator) {
			ObjectClassGenerator objectClassGenerator = (ObjectClassGenerator) generator;
			objectClassGenerator.setObjectClass(type);
		}
		return generator;
	}

	private <T> Generator<T> lookupGenerator(Class<?> type) {

		// class lookup
		Generator<T> generator = getFromRegistry(type, type);
		if (generator != null) {
			return generator;
		}

		// interfaces lookup
		List<Class<?>> interfaces = ClassUtils.getAllInterfaces(type);
		for (Class<?> interfaceClass : interfaces) {
			generator = getFromRegistry(type, interfaceClass);
			if (generator != null) {
				return generator;
			}
		}

		return generator;
	}

	private <T> Generator<T> getFromRegistry(Class<?> generatedObjectClass, Class<?> classInRegistry) {
		Generator<T> generator = null;
		Object registeredObject = registry.get(classInRegistry);
		if (registeredObject instanceof Generator) {
			generator = (Generator<T>) registeredObject;
		} else if (registeredObject != null) {
			Class<T> generatorClass = (Class<T>) registeredObject;
			try {
				try {
					generator = (Generator<T>) invokeConstructor(generatorClass, generatedObjectClass);
				} catch (NoSuchMethodException e) {
					generator = (Generator<T>) invokeConstructor(generatorClass, null);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return generator;
	}

}
