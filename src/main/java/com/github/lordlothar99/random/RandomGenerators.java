package com.github.lordlothar99.random;

import static com.github.lordlothar99.random.RandomToolkit.BIG_DECIMAL;
import static com.github.lordlothar99.random.RandomToolkit.BIG_INTEGER;
import static com.github.lordlothar99.random.RandomToolkit.BOOLEAN;
import static com.github.lordlothar99.random.RandomToolkit.BYTE;
import static com.github.lordlothar99.random.RandomToolkit.CALENDAR;
import static com.github.lordlothar99.random.RandomToolkit.DATE;
import static com.github.lordlothar99.random.RandomToolkit.DATE_TIME;
import static com.github.lordlothar99.random.RandomToolkit.DOUBLE;
import static com.github.lordlothar99.random.RandomToolkit.FLOAT;
import static com.github.lordlothar99.random.RandomToolkit.INTEGER;
import static com.github.lordlothar99.random.RandomToolkit.LOCAL_DATE;
import static com.github.lordlothar99.random.RandomToolkit.LOCAL_DATE_TIME;
import static com.github.lordlothar99.random.RandomToolkit.LONG;
import static com.github.lordlothar99.random.RandomToolkit.SHORT;
import static com.github.lordlothar99.random.RandomToolkit.STRING;
import static com.github.lordlothar99.random.RandomToolkit.XMLGREGORIANCALENDAR;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.impl.RandomEnumGenerator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.collections.RandomCollectionGenerator;
import com.github.lordlothar99.random.impl.collections.RandomMapGenerator;

public class RandomGenerators {

	private Map<Class<?>, Object> registry = new HashMap<Class<?>, Object>();

	public RandomGenerators() {
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

}
