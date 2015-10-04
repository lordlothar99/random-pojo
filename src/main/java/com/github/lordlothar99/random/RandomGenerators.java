package com.github.lordlothar99.random;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.RangedGenerator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.RandomStringGenerator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomGenerators {

	private RandomGeneratorsRegistry registry;

	public RandomGenerators() {
		this(new RandomGeneratorsRegistry());
	}

	public RandomGenerators(RandomGeneratorsRegistry registry) {
		super();
		this.registry = registry;
	}

	public RangedGenerator<Boolean> booleanGenerator() {
		return (RangedGenerator<Boolean>) registry.getGenerator(Boolean.class);
	}

	public RangedGenerator<Boolean> booleanGenerator(boolean min, boolean max) {
		RangedGenerator<Boolean> generator = booleanGenerator();
		initRange(generator, min, max);
		return generator;
	}

	public RangedGenerator<Byte> byteGenerator() {
		return (RangedGenerator<Byte>) registry.getGenerator(Byte.class);
	}

	public RangedGenerator<Byte> byteGenerator(byte min, byte max) {
		RangedGenerator<Byte> generator = byteGenerator();
		initRange(generator, min, max);
		return generator;
	}

	public RangedGenerator<Short> shortGenerator() {
		return (RangedGenerator<Short>) registry.getGenerator(Short.class);
	}

	public RangedGenerator<Short> shortGenerator(short min, short max) {
		RangedGenerator<Short> generator = shortGenerator();
		initRange(generator, min, max);
		return generator;
	}

	public RangedGenerator<Integer> integerGenerator() {
		return (RangedGenerator<Integer>) registry.getGenerator(Integer.class);
	}

	public RangedGenerator<Integer> integerGenerator(int min, int max) {
		RangedGenerator<Integer> generator = integerGenerator();
		initRange(generator, min, max);
		return generator;
	}

	public RangedGenerator<Long> longGenerator() {
		return (RangedGenerator<Long>) registry.getGenerator(Long.class);
	}

	public RangedGenerator<Long> longGenerator(long min, long max) {
		RangedGenerator<Long> generator = longGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<Date> dateGenerator() {
		return (RangedGenerator<Date>) registry.getGenerator(Date.class);
	}

	public RangedGenerator<Date> getDate(Date min, Date max) {
		RangedGenerator<Date> generator = dateGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<Calendar> calendarGenerator() {
		return (RangedGenerator<Calendar>) registry.getGenerator(Calendar.class);
	}

	public RangedGenerator<Calendar> getCalendar(Calendar min, Calendar max) {
		RangedGenerator<Calendar> generator = calendarGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<DateTime> dateTimeGenerator() {
		return (RangedGenerator<DateTime>) registry.getGenerator(DateTime.class);
	}

	public RangedGenerator<DateTime> getCalendar(DateTime min, DateTime max) {
		RangedGenerator<DateTime> generator = dateTimeGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<LocalDate> localDateGenerator() {
		return (RangedGenerator<LocalDate>) registry.getGenerator(LocalDate.class);
	}

	public RangedGenerator<LocalDate> getCalendar(LocalDate min, LocalDate max) {
		RangedGenerator<LocalDate> generator = localDateGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<LocalDateTime> localDateTimeGenerator() {
		return (RangedGenerator<LocalDateTime>) registry.getGenerator(LocalDateTime.class);
	}

	public RangedGenerator<LocalDateTime> getCalendar(LocalDateTime min, LocalDateTime max) {
		RangedGenerator<LocalDateTime> generator = localDateTimeGenerator();
		return initRange(generator, min, max);
	}

	public RangedGenerator<XMLGregorianCalendar> xmlGregorianCalendarGenerator() {
		return (RangedGenerator<XMLGregorianCalendar>) registry.getGenerator(XMLGregorianCalendar.class);
	}

	public RangedGenerator<XMLGregorianCalendar> xmlGregorianCalendarGenerator(XMLGregorianCalendar min,
			XMLGregorianCalendar max) {
		RangedGenerator<XMLGregorianCalendar> generator = xmlGregorianCalendarGenerator();
		return initRange(generator, min, max);
	}

	private <T> RangedGenerator<T> initRange(RangedGenerator<T> generator, T min, T max) {
		generator.setMin(min);
		generator.setMax(max);
		return generator;
	}

	public RandomStringGenerator stringGenerator() {
		return (RandomStringGenerator) registry.getGenerator(String.class);
	}

	public RandomStringGenerator stringGenerator(int length) {
		RandomStringGenerator stringGenerator = stringGenerator();
		stringGenerator.setLength(length);
		return stringGenerator;
	}

	public RandomStringGenerator stringGenerator(int length, boolean letters, boolean numbers) {
		RandomStringGenerator stringGenerator = stringGenerator(length);
		stringGenerator.setLetters(letters);
		stringGenerator.setNumbers(numbers);
		return stringGenerator;
	}

	public RandomStringGenerator stringGenerator(int length, char[] chars) {
		RandomStringGenerator stringGenerator = stringGenerator(length);
		stringGenerator.setChars(chars);
		return stringGenerator;
	}

	public <T> RandomObjectGenerator<T> objectGenerator(Class<T> objectClass) {
		return (RandomObjectGenerator<T>) registry.getGenerator(objectClass);
	}

	public <T> ContainerGenerator<List<T>> listGenerator(Class<T> elementsType) {
		ContainerGenerator<List> listGenerator = (ContainerGenerator<List>) registry.getGenerator(List.class);
		listGenerator.setElementsTypes(elementsType);
		return cast(listGenerator);
	}

	public <T> ContainerGenerator<Set<Integer>> setGenerator(Class<T> elementsType) {
		ContainerGenerator<Set> setGenerator = (ContainerGenerator<Set>) registry.getGenerator(Set.class);
		setGenerator.setElementsTypes(elementsType);
		return cast(setGenerator);
	}

	public <T> ContainerGenerator<Set<Integer>> setGenerator(Class<T> elementsType, int size) {
		ContainerGenerator<Set<Integer>> setGenerator = setGenerator(elementsType);
		setGenerator.setSize(size);
		return setGenerator;
	}

	public <T> ContainerGenerator<Set<Integer>> setGenerator(Class<T> elementsType, int minSize, int maxSize) {
		ContainerGenerator<Set<Integer>> setGenerator = setGenerator(elementsType);
		setGenerator.setMinSize(minSize);
		setGenerator.setMaxSize(maxSize);
		return setGenerator;
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Class<K> keyType, Class<V> valueType) {
		ContainerGenerator<Map> mapGenerator = (ContainerGenerator<Map>) registry.getGenerator(Map.class);
		mapGenerator.setElementsTypes(keyType, valueType);
		return cast(mapGenerator);
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Class<K> keyType, Class<V> valueType, int size) {
		ContainerGenerator<Map<K, V>> mapGenerator = mapGenerator(keyType, valueType);
		mapGenerator.setSize(size);
		return mapGenerator;
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Class<K> keyType, Class<V> valueType, int minSize,
			int maxSize) {
		ContainerGenerator<Map<K, V>> mapGenerator = mapGenerator(keyType, valueType);
		mapGenerator.setMinSize(minSize);
		mapGenerator.setMaxSize(maxSize);
		return cast(mapGenerator);
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Generator<K> keyGenerator, Generator<V> valueGenerator) {
		ContainerGenerator<Map> mapGenerator = (ContainerGenerator<Map>) registry.getGenerator(Map.class);
		mapGenerator.setElementsGenerators(keyGenerator, valueGenerator);
		return cast(mapGenerator);
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Generator<K> keyGenerator, Generator<V> valueGenerator, int size) {
		ContainerGenerator<Map<K, V>> mapGenerator = mapGenerator(keyGenerator, valueGenerator);
		mapGenerator.setSize(size);
		return mapGenerator;
	}

	public <K, V> ContainerGenerator<Map<K, V>> mapGenerator(Generator<K> keyGenerator, Generator<V> valueGenerator, int minSize,
			int maxSize) {
		ContainerGenerator<Map<K, V>> mapGenerator = mapGenerator(keyGenerator, valueGenerator);
		mapGenerator.setMinSize(minSize);
		mapGenerator.setMaxSize(maxSize);
		return cast(mapGenerator);
	}

	public <K, V> ContainerGenerator<TreeMap<K, V>> treemapGenerator(Class<K> keyType, Class<V> valueType) {
		ContainerGenerator<TreeMap> mapGenerator = (ContainerGenerator<TreeMap>) registry.getGenerator(TreeMap.class);
		mapGenerator.setElementsTypes(keyType, valueType);
		return cast(mapGenerator);
	}

	public <E extends Enum<E>> Generator<E> enumGenerator(Class<E> enumClass) {
		return registry.getGenerator(enumClass);
	}

	public <T> Generator<T> arrayGenerator(Class<T> arrayClass) {
		return registry.getGenerator(arrayClass);
	}

	private <T> T cast(Object object) {
		return (T) object;
	}
}
