package com.github.lordlothar99.random;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import com.github.lordlothar99.random.impl.string.RandomCharacterGenerator;
import com.github.lordlothar99.random.impl.string.RandomEmailGenerator;
import com.github.lordlothar99.random.impl.string.RandomStringGenerator;
import com.github.lordlothar99.random.impl.string.AbstractRandomCharactersGenerator.Case;


public interface IRandomGenerators {

  <G extends Generator<Boolean>> G booleanGenerator();

  <G extends RangedGenerator<Byte>> G byteGenerator();

  <G extends RangedGenerator<Byte>> G byteGenerator(byte min, byte max);

  <G extends RangedGenerator<Short>> G shortGenerator();

  <G extends RangedGenerator<Short>> G shortGenerator(short min, short max);

  <G extends RangedGenerator<Integer>> G integerGenerator();

  <G extends RangedGenerator<Integer>> G integerGenerator(int min, int max);

  <G extends RangedGenerator<Long>> G longGenerator();

  <G extends RangedGenerator<Long>> G longGenerator(long min, long max);

  <G extends RangedGenerator<Float>> G floatGenerator();

  <G extends RangedGenerator<Float>> G floatGenerator(float min, float max);

  <G extends RangedGenerator<Double>> G doubleGenerator();

  <G extends RangedGenerator<Double>> G doubleGenerator(double min, double max);

  <G extends RangedGenerator<BigDecimal>> G bigDecimalGenerator();

  <G extends RangedGenerator<BigDecimal>> G bigDecimalGenerator(BigDecimal min, BigDecimal max);

  <G extends RangedGenerator<BigInteger>> G bigIntegerGenerator();

  <G extends RangedGenerator<BigInteger>> G bigIntegerGenerator(BigInteger min, BigInteger max);

  <G extends RangedGenerator<Date>> G dateGenerator();

  <G extends RangedGenerator<Date>> G dateGenerator(Date min, Date max);

  <G extends RangedGenerator<Calendar>> G calendarGenerator();

  <G extends RangedGenerator<Calendar>> G calendarGenerator(Calendar min, Calendar max);

  <G extends RangedGenerator<DateTime>> G dateTimeGenerator();

  <G extends RangedGenerator<DateTime>> G dateTimeGenerator(DateTime min, DateTime max);

  <G extends RangedGenerator<LocalDate>> G localDateGenerator();

  <G extends RangedGenerator<LocalDate>> G localDateGenerator(LocalDate min, LocalDate max);

  <G extends RangedGenerator<LocalDateTime>> G localDateTimeGenerator();

  <G extends RangedGenerator<LocalDateTime>> G localDateTimeGenerator(LocalDateTime min,
      LocalDateTime max);

  <G extends RangedGenerator<XMLGregorianCalendar>> G xmlGregorianCalendarGenerator();

  <G extends RangedGenerator<XMLGregorianCalendar>> G xmlGregorianCalendarGenerator(
      XMLGregorianCalendar min, XMLGregorianCalendar max);

  <T, G extends RangedGenerator<T>> G withRange(G generator, T min, T max);

  <G extends RandomStringGenerator> G stringGenerator();

  <G extends RandomStringGenerator> G stringGenerator(int length);

  <G extends RandomStringGenerator> G stringGenerator(int length, boolean letters, boolean numbers);

  <G extends RandomStringGenerator> G stringGenerator(int length, char[] chars);

  <G extends RandomStringGenerator> G stringGenerator(Case letterCase);

  <G extends RandomStringGenerator> G stringGenerator(int length, Case letterCase);

  <G extends RandomStringGenerator> G stringGenerator(int length, boolean letters, boolean numbers,
      Case letterCase);

  <G extends RandomStringGenerator> G stringGenerator(int length, char[] chars, Case letterCase);

  <G extends RandomCharacterGenerator> G charGenerator();

  <G extends RandomCharacterGenerator> G charGenerator(boolean letters, boolean numbers);

  <G extends RandomCharacterGenerator> G charGenerator(char[] chars);

  <G extends RandomCharacterGenerator> G charGenerator(Case letterCase);

  <G extends RandomCharacterGenerator> G charGenerator(boolean letters, boolean numbers,
      Case letterCase);

  <G extends RandomCharacterGenerator> G charGenerator(char[] chars, Case letterCase);

  RandomEmailGenerator emailGenerator();

  RandomEmailGenerator emailGenerator(int length);

  RandomEmailGenerator emailGenerator(int length, boolean letters, boolean numbers);

  RandomEmailGenerator emailGenerator(int length, char[] chars);

  <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass);

  <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass, String fieldName,
      Generator<?> generator);

  <T, G extends RandomObjectGenerator<T>> G withFieldGenerator(G objectGenerator, String fieldName,
      Generator<?> generator);

  <T, G extends RandomObjectGenerator<T>> G withSkippedField(G objectGenerator, String fieldName);

  <T, G extends RandomObjectGenerator<T>> G withSkippedFields(G objectGenerator,
      String... fieldName);

  <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass,
      String... skippedField);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType, int size);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType, int minSize, int maxSize);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator, int size);

  <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator, int minSize, int maxSize);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType, int size);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType, int minSize,
      int maxSize);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator, int size);

  <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator, int minSize,
      int maxSize);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType, int size);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType, int minSize,
      int maxSize);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator, int size);

  <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator, int minSize,
      int maxSize);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType, int size);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType, int minSize, int maxSize);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int size);

  <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int minSize, int maxSize);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType, int size);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType, int minSize, int maxSize);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int size);

  <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int minSize, int maxSize);

  <E extends Enum<E>> Generator<E> enumGenerator(Class<E> enumClass);

  <T> Generator<T> arrayGenerator(Class<T> arrayClass);

  <T> Generator<T> elementGenerator(Object container);

  RandomGeneratorsRegistry registry();

}
