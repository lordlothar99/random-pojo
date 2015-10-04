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
import com.github.lordlothar99.random.impl.RandomBooleanGenerator;
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
import com.github.lordlothar99.random.impl.numeric.RandomByteGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomDoubleGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomFloatGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomLongGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomShortGenerator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomGeneratorsRegistry {

	private Map<Class<?>, Class<? extends Generator>> registry = new HashMap<Class<?>, Class<? extends Generator>>();

	public RandomGeneratorsRegistry() {
		registry.put(BigDecimal.class, RandomBigDecimalGenerator.class);
		registry.put(Boolean.class, RandomBooleanGenerator.class);
		registry.put(boolean.class, RandomBooleanGenerator.class);
		registry.put(Calendar.class, RandomCalendarGenerator.class);
		registry.put(Date.class, RandomDateGenerator.class);
		registry.put(BigInteger.class, RandomBigIntegerGenerator.class);
		registry.put(Integer.class, RandomIntegerGenerator.class);
		registry.put(int.class, RandomIntegerGenerator.class);
		registry.put(Long.class, RandomLongGenerator.class);
		registry.put(long.class, RandomLongGenerator.class);
		registry.put(Short.class, RandomShortGenerator.class);
		registry.put(short.class, RandomShortGenerator.class);
		registry.put(Byte.class, RandomByteGenerator.class);
		registry.put(byte.class, RandomByteGenerator.class);
		registry.put(Double.class, RandomDoubleGenerator.class);
		registry.put(double.class, RandomDoubleGenerator.class);
		registry.put(Float.class, RandomFloatGenerator.class);
		registry.put(float.class, RandomFloatGenerator.class);
		registry.put(String.class, RandomStringGenerator.class);
		try {
			registry.put(Class.forName("org.joda.time.LocalDate"), RandomLocalDateGenerator.class);
			registry.put(Class.forName("org.joda.time.LocalDateTime"), RandomLocalDateTimeGenerator.class);
			registry.put(Class.forName("org.joda.time.DateTime"), RandomDateTimeGenerator.class);
		} catch (ClassNotFoundException e) {
			// joda unavailable
		}
		registry.put(XMLGregorianCalendar.class, RandomXMLGregorianCalendarGenerator.class);

		registry.put(Enum.class, RandomEnumGenerator.class);
		registry.put(Collection.class, RandomCollectionGenerator.class);
		registry.put(Map.class, RandomMapGenerator.class);
		registry.put(Array.class, RandomArrayGenerator.class);
		registry.put(Object.class, RandomObjectGenerator.class);
	}

	public <T> void put(Class<?> type, Class<? extends Generator> generatorClass) {
		registry.put(type, generatorClass);
	}

	public <T> Generator<T> getGenerator(Class<T> type) {

		Generator<T> generator = null;

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
		if (classInRegistry.isArray()) {
			classInRegistry = Array.class;
		}
		Object registeredObject = registry.get(classInRegistry);
		Generator<T> generator = null;
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
