/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.github.lordlothar99.random.api.Generator;

/**
 * Toolkit for {@link Generator}.
 *
 * @author Francois Lecomte
 */
public class RandomToolkit {

	private static final RandomToolkit INSTANCE = new RandomToolkit();

	private RandomGeneratorsRegistry registry;
	private RandomGenerators generators;

	public RandomToolkit() {
		this(new RandomGenerators());
	}

	public RandomToolkit(RandomGenerators generators) {
		super();
		this.generators = generators;
		this.registry = generators.registry();
	}

	public RandomToolkit(RandomGeneratorsRegistry registry) {
		super();
		this.registry = registry;
		this.generators = new RandomGenerators(registry);
	}

	public static RandomToolkit get() {
		return INSTANCE;
	}

	public RandomGenerators generators() {
		return generators;
	}

	public RandomGeneratorsRegistry registry() {
		return registry;
	}

	// ---

	public <T> T generate(Class<T> type) {
		Generator<T> generateur = registry.getGenerator(type);
		return generateur.create();
	}

	public Object[] generate(Class<?>[] types) {
		Object[] values = new Object[types.length];
		for (int i = 0; i < values.length; i++) {
			Class<?> type = types[i];
			values[i] = generate(type);
		}
		return values;
	}

	// ---

	public boolean booleanValue() {
		return generators.booleanGenerator().create();
	}

	public byte byteValue() {
		return generators.byteGenerator().create();
	}

	public byte byteValue(byte min, byte max) {
		return generators.byteGenerator(min, max).create();
	}

	public short shortValue() {
		return generators.shortGenerator().create();
	}

	public short shortValue(short min, short max) {
		return generators.shortGenerator(min, max).create();
	}

	public int intValue() {
		return generators.integerGenerator().create();
	}

	public int intValue(int min, int max) {
		return generators.integerGenerator(min, max).create();
	}

	public long longValue() {
		return generators.longGenerator().create();
	}

	public long longValue(long min, long max) {
		return generators.longGenerator(min, max).create();
	}

	public float floatValue() {
		return generators.floatGenerator().create();
	}

	public float floatValue(float min, float max) {
		return generators.floatGenerator(min, max).create();
	}

	public double doubleValue() {
		return generators.doubleGenerator().create();
	}

	public double doubleValue(double min, double max) {
		return generators.doubleGenerator(min, max).create();
	}

	public BigDecimal bigDecimalValue() {
		return generators.bigDecimalGenerator().create();
	}

	public BigDecimal bigDecimalValue(BigDecimal min, BigDecimal max) {
		return generators.bigDecimalGenerator(min, max).create();
	}

	public BigInteger bigIntegerValue() {
		return generators.bigIntegerGenerator().create();
	}

	public BigInteger bigIntegerValue(BigInteger min, BigInteger max) {
		return generators.bigIntegerGenerator(min, max).create();
	}

	// ---

	public Date date() {
		return generators.dateGenerator().create();
	}

	public Date date(Date min, Date max) {
		return generators.dateGenerator(min, max).create();
	}

	public Calendar calendar() {
		return generators.calendarGenerator().create();
	}

	public Calendar calendar(Calendar min, Calendar max) {
		return generators.calendarGenerator(min, max).create();
	}

	public DateTime dateTime() {
		return generators.dateTimeGenerator().create();
	}

	public DateTime dateTime(DateTime min, DateTime max) {
		return generators.dateTimeGenerator(min, max).create();
	}

	public LocalDate localDate() {
		return generators.localDateGenerator().create();
	}

	public LocalDate localDate(LocalDate min, LocalDate max) {
		return generators.localDateGenerator(min, max).create();
	}

	public LocalDateTime localDateTime() {
		return generators.localDateTimeGenerator().create();
	}

	public LocalDateTime localDateTime(LocalDateTime min, LocalDateTime max) {
		return generators.localDateTimeGenerator(min, max).create();
	}

	public XMLGregorianCalendar xmlGregorianCalendar() {
		return generators.xmlGregorianCalendarGenerator().create();
	}

	public XMLGregorianCalendar xmlGregorianCalendar(XMLGregorianCalendar min, XMLGregorianCalendar max) {
		return generators.xmlGregorianCalendarGenerator(min, max).create();
	}

	// ---

	public String string() {
		return generators.stringGenerator().create();
	}

	public String string(int length) {
		return generators.stringGenerator(length).create();
	}

	public String string(int length, boolean letters, boolean numbers) {
		return generators.stringGenerator(length, letters, numbers).create();
	}

	public String string(int length, char[] chars) {
		return generators.stringGenerator(length, chars).create();
	}

	public char character() {
		return generators.charGenerator().create();
	}

	public char character(boolean letters, boolean numbers) {
		return generators.charGenerator(letters, numbers).create();
	}

	public char character(char[] chars) {
		return generators.charGenerator(chars).create();
	}

	// ---

	public <T> T object(Class<T> objectClass) {
		return generators.objectGenerator(objectClass).create();
	}

	public <T> T object(Class<T> objectClass, String fieldName, Generator<?> generator) {
		return generators.objectGenerator(objectClass, fieldName, generator).create();
	}

	public <T> T object(Class<T> objectClass, String... skippedField) {
		return generators.objectGenerator(objectClass, skippedField).create();
	}

	// ---

	public <C extends Collection<T>, T> C collection(Class<C> collectionType, Class<T> elementsType) {
		return generators.collectionGenerator(collectionType, elementsType).create();
	}

	public <C extends Collection<T>, T> C collection(Class<C> collectionType, Class<T> elementsType, int size) {
		return generators.collectionGenerator(collectionType, elementsType, size).create();
	}

	public <C extends Collection<T>, T> C collection(Class<C> collectionType, Class<T> elementsType, int minSize,
			int maxSize) {
		return generators.collectionGenerator(collectionType, elementsType, minSize, maxSize).create();
	}

	// ---

	public <T> List<T> list(Class<T> elementsType) {
		return generators.listGenerator(elementsType).create();
	}

	public <T> List<T> list(Class<T> elementsType, int size) {
		return generators.listGenerator(elementsType, size).create();
	}

	public <T> List<T> list(Class<T> elementsType, int minSize, int maxSize) {
		return generators.listGenerator(elementsType, minSize, maxSize).create();
	}

	// ---

	public <T> Set<T> set(Class<T> elementsType) {
		return generators.setGenerator(elementsType).create();
	}

	public <T> Set<T> set(Class<T> elementsType, int size) {
		return generators.setGenerator(elementsType, size).create();
	}

	public <T> Set<T> set(Class<T> elementsType, int minSize, int maxSize) {
		return generators.setGenerator(elementsType, minSize, maxSize).create();
	}

	// ---

	public <K, V> Map<K, V> map(Class<K> keyType, Class<V> valueType) {
		return generators.mapGenerator(keyType, valueType).create();
	}

	public <K, V> Map<K, V> map(Class<K> keyType, Class<V> valueType, int size) {
		return generators.mapGenerator(keyType, valueType, size).create();
	}

	public <K, V> Map<K, V> map(Class<K> keyType, Class<V> valueType, int minSize, int maxSize) {
		return generators.mapGenerator(keyType, valueType, minSize, maxSize).create();
	}

	public <K, V> Map<K, V> map(Generator<K> keyGenerator, Generator<V> valueGenerator) {
		return generators.mapGenerator(keyGenerator, valueGenerator).create();
	}

	public <K, V> Map<K, V> map(Generator<K> keyGenerator, Generator<V> valueGenerator, int size) {
		return generators.mapGenerator(keyGenerator, valueGenerator, size).create();
	}

	public <K, V> Map<K, V> map(Generator<K> keyGenerator, Generator<V> valueGenerator, int minSize, int maxSize) {
		return generators.mapGenerator(keyGenerator, valueGenerator, minSize, maxSize).create();
	}

	// ---

	public <K, V> TreeMap<K, V> treeMap(Class<K> keyType, Class<V> valueType) {
		return generators.treeMapGenerator(keyType, valueType).create();
	}

	public <K, V> TreeMap<K, V> treeMap(Class<K> keyType, Class<V> valueType, int size) {
		return generators.treeMapGenerator(keyType, valueType, size).create();
	}

	public <K, V> TreeMap<K, V> treeMap(Class<K> keyType, Class<V> valueType, int minSize, int maxSize) {
		return generators.treeMapGenerator(keyType, valueType, minSize, maxSize).create();
	}

	public <K, V> TreeMap<K, V> treeMap(Generator<K> keyGenerator, Generator<V> valueGenerator) {
		return generators.treeMapGenerator(keyGenerator, valueGenerator).create();
	}

	public <K, V> TreeMap<K, V> treeMap(Generator<K> keyGenerator, Generator<V> valueGenerator, int size) {
		return generators.treeMapGenerator(keyGenerator, valueGenerator, size).create();
	}

	public <K, V> TreeMap<K, V> treeMap(Generator<K> keyGenerator, Generator<V> valueGenerator, int minSize,
			int maxSize) {
		return generators.treeMapGenerator(keyGenerator, valueGenerator, minSize, maxSize).create();
	}

	// ---

	public <E extends Enum<E>> E enumValue(Class<E> enumClass) {
		return generators.enumGenerator(enumClass).create();
	}

	public <T> T array(Class<T> arrayClass) {
		return generators.arrayGenerator(arrayClass).create();
	}

	public <E> E element(Collection<E> collection) {
		return elementFrom(collection);
	}

	public <K, V> Map.Entry<K, V> element(Map<K, V> map) {
		return elementFrom(map);
	}

	public <E> E element(E[] array) {
		return elementFrom(array);
	}

	public byte element(byte[] array) {
		return elementFrom(array);
	}

	public short element(short[] array) {
		return elementFrom(array);
	}

	public int element(int[] array) {
		return elementFrom(array);
	}

	public long element(long[] array) {
		return elementFrom(array);
	}

	public float element(float[] array) {
		return elementFrom(array);
	}

	public double element(double[] array) {
		return elementFrom(array);
	}

	@SuppressWarnings("unchecked")
	protected <E> E elementFrom(Object container) {
		return (E) generators.elementGenerator(container).create();
	}
}
