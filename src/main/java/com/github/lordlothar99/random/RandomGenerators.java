package com.github.lordlothar99.random;

import static com.github.lordlothar99.random.RandomToolkit.cast;

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

import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.RangedGenerator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.element.RandomElementGenerator;
import com.github.lordlothar99.random.impl.string.AbstractRandomCharactersGenerator.Case;
import com.github.lordlothar99.random.impl.string.RandomCharacterGenerator;
import com.github.lordlothar99.random.impl.string.RandomEmailGenerator;
import com.github.lordlothar99.random.impl.string.RandomStringGenerator;

@SuppressWarnings({"unchecked", "rawtypes"})
public class RandomGenerators implements IRandomGenerators {

  private RandomGeneratorsRegistry registry;

  public RandomGenerators() {
    this(new RandomGeneratorsRegistry());
  }

  public RandomGenerators(RandomGeneratorsRegistry registry) {
    super();
    this.registry = registry;
  }

  @Override
  public <G extends Generator<Boolean>> G booleanGenerator() {
    return (G) registry.getGenerator(Boolean.class);
  }

  @Override
  public <G extends RangedGenerator<Byte>> G byteGenerator() {
    return (G) registry.getGenerator(Byte.class);
  }

  @Override
  public <G extends RangedGenerator<Byte>> G byteGenerator(byte min, byte max) {
    return withRange(this.<G>byteGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Short>> G shortGenerator() {
    return (G) registry.getGenerator(Short.class);
  }

  @Override
  public <G extends RangedGenerator<Short>> G shortGenerator(short min, short max) {
    return withRange(this.<G>shortGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Integer>> G integerGenerator() {
    return (G) registry.getGenerator(Integer.class);
  }

  @Override
  public <G extends RangedGenerator<Integer>> G integerGenerator(int min, int max) {
    return withRange(this.<G>integerGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Long>> G longGenerator() {
    return (G) registry.getGenerator(Long.class);
  }

  @Override
  public <G extends RangedGenerator<Long>> G longGenerator(long min, long max) {
    return withRange(this.<G>longGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Float>> G floatGenerator() {
    return (G) registry.getGenerator(Float.class);
  }

  @Override
  public <G extends RangedGenerator<Float>> G floatGenerator(float min, float max) {
    return withRange(this.<G>floatGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Double>> G doubleGenerator() {
    return (G) registry.getGenerator(Double.class);
  }

  @Override
  public <G extends RangedGenerator<Double>> G doubleGenerator(double min, double max) {
    return withRange(this.<G>doubleGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<BigDecimal>> G bigDecimalGenerator() {
    return (G) registry.getGenerator(BigDecimal.class);
  }

  @Override
  public <G extends RangedGenerator<BigDecimal>> G bigDecimalGenerator(BigDecimal min,
      BigDecimal max) {
    return withRange(this.<G>bigDecimalGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<BigInteger>> G bigIntegerGenerator() {
    return (G) registry.getGenerator(BigInteger.class);
  }

  @Override
  public <G extends RangedGenerator<BigInteger>> G bigIntegerGenerator(BigInteger min,
      BigInteger max) {
    return withRange(this.<G>bigIntegerGenerator(), min, max);
  }

  // ---

  @Override
  public <G extends RangedGenerator<Date>> G dateGenerator() {
    return (G) registry.getGenerator(Date.class);
  }

  @Override
  public <G extends RangedGenerator<Date>> G dateGenerator(Date min, Date max) {
    return withRange(this.<G>dateGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<Calendar>> G calendarGenerator() {
    return (G) registry.getGenerator(Calendar.class);
  }

  @Override
  public <G extends RangedGenerator<Calendar>> G calendarGenerator(Calendar min, Calendar max) {
    return withRange(this.<G>calendarGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<DateTime>> G dateTimeGenerator() {
    return (G) registry.getGenerator(DateTime.class);
  }

  @Override
  public <G extends RangedGenerator<DateTime>> G dateTimeGenerator(DateTime min, DateTime max) {
    return withRange(this.<G>dateTimeGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<LocalDate>> G localDateGenerator() {
    return (G) registry.getGenerator(LocalDate.class);
  }

  @Override
  public <G extends RangedGenerator<LocalDate>> G localDateGenerator(LocalDate min, LocalDate max) {
    return withRange(this.<G>localDateGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<LocalDateTime>> G localDateTimeGenerator() {
    return (G) registry.getGenerator(LocalDateTime.class);
  }

  @Override
  public <G extends RangedGenerator<LocalDateTime>> G localDateTimeGenerator(LocalDateTime min,
      LocalDateTime max) {
    return withRange(this.<G>localDateTimeGenerator(), min, max);
  }

  @Override
  public <G extends RangedGenerator<XMLGregorianCalendar>> G xmlGregorianCalendarGenerator() {
    return (G) registry.getGenerator(XMLGregorianCalendar.class);
  }

  @Override
  public <G extends RangedGenerator<XMLGregorianCalendar>> G xmlGregorianCalendarGenerator(
      XMLGregorianCalendar min, XMLGregorianCalendar max) {
    return withRange(this.<G>xmlGregorianCalendarGenerator(), min, max);
  }

  @Override
  public <T, G extends RangedGenerator<T>> G withRange(G generator, T min, T max) {
    generator.setMin(min);
    generator.setMax(max);
    return generator;
  }

  // ---

  @Override
  public <G extends RandomStringGenerator> G stringGenerator() {
    return (G) registry.getGenerator(String.class);
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length) {
    G stringGenerator = stringGenerator();
    stringGenerator.setLength(length);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length, boolean letters,
      boolean numbers) {
    G stringGenerator = stringGenerator(length);
    stringGenerator.setLetters(letters);
    stringGenerator.setNumbers(numbers);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length, char[] chars) {
    G stringGenerator = stringGenerator(length);
    stringGenerator.setChars(chars);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(Case letterCase) {
    G stringGenerator = stringGenerator();
    stringGenerator.setCase(letterCase);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length, Case letterCase) {
    G stringGenerator = stringGenerator(length);
    stringGenerator.setCase(letterCase);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length, boolean letters,
      boolean numbers, Case letterCase) {
    G stringGenerator = stringGenerator(length, letters, numbers);
    stringGenerator.setCase(letterCase);
    return stringGenerator;
  }

  @Override
  public <G extends RandomStringGenerator> G stringGenerator(int length, char[] chars,
      Case letterCase) {
    G stringGenerator = stringGenerator(length, chars);
    stringGenerator.setCase(letterCase);
    return stringGenerator;
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator() {
    return (G) registry.getGenerator(Character.class);
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator(boolean letters, boolean numbers) {
    G stringGenerator = charGenerator();
    stringGenerator.setLetters(letters);
    stringGenerator.setNumbers(numbers);
    return stringGenerator;
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator(char[] chars) {
    G stringGenerator = charGenerator();
    stringGenerator.setChars(chars);
    return stringGenerator;
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator(Case letterCase) {
    G charGenerator = charGenerator();
    charGenerator.setCase(letterCase);
    return charGenerator;
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator(boolean letters, boolean numbers,
      Case letterCase) {
    G charGenerator = charGenerator(letters, numbers);
    charGenerator.setCase(letterCase);
    return charGenerator;
  }

  @Override
  public <G extends RandomCharacterGenerator> G charGenerator(char[] chars, Case letterCase) {
    G charGenerator = charGenerator(chars);
    charGenerator.setCase(letterCase);
    return charGenerator;
  }

  @Override
  public RandomEmailGenerator emailGenerator() {
    return new RandomEmailGenerator();
  }

  @Override
  public RandomEmailGenerator emailGenerator(int length) {
    RandomEmailGenerator stringGenerator = emailGenerator();
    stringGenerator.setLength(length);
    return stringGenerator;
  }

  @Override
  public RandomEmailGenerator emailGenerator(int length, boolean letters, boolean numbers) {
    RandomEmailGenerator stringGenerator = emailGenerator(length);
    stringGenerator.setLetters(letters);
    stringGenerator.setNumbers(numbers);
    return stringGenerator;
  }

  @Override
  public RandomEmailGenerator emailGenerator(int length, char[] chars) {
    RandomEmailGenerator stringGenerator = emailGenerator(length);
    stringGenerator.setChars(chars);
    return stringGenerator;
  }

  // ---

  @Override
  public <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass) {
    return (G) registry.getGenerator(objectClass);
  }

  @Override
  public <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass,
      String fieldName, Generator<?> generator) {
    return withFieldGenerator(this.<T, G>objectGenerator(objectClass), fieldName, generator);
  }

  @Override
  public <T, G extends RandomObjectGenerator<T>> G withFieldGenerator(G objectGenerator,
      String fieldName, Generator<?> generator) {
    objectGenerator.setFieldGenerator(fieldName, generator);
    return objectGenerator;
  }

  @Override
  public <T, G extends RandomObjectGenerator<T>> G withSkippedField(G objectGenerator,
      String fieldName) {
    objectGenerator.addSkippedField(fieldName);
    return objectGenerator;
  }

  @Override
  public <T, G extends RandomObjectGenerator<T>> G withSkippedFields(G objectGenerator,
      String... fieldName) {
    objectGenerator.setSkippedFields(fieldName);
    return objectGenerator;
  }

  @Override
  public <T, G extends RandomObjectGenerator<T>> G objectGenerator(Class<T> objectClass,
      String... skippedField) {
    return withSkippedFields(this.<T, G>objectGenerator(objectClass), skippedField);
  }

  // ---

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType) {
    ContainerGenerator<Collection> collectionGenerator =
        (ContainerGenerator<Collection>) registry.getGenerator(collectionType);
    collectionGenerator.setElementsTypes(elementsType);
    return cast(collectionGenerator);
  }

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType, int size) {
    G collectionGenerator = collectionGenerator(collectionType, elementsType);
    collectionGenerator.setSize(size);
    return collectionGenerator;
  }

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Class<T> elementsType, int minSize, int maxSize) {
    G collectionGenerator = collectionGenerator(collectionType, elementsType);
    collectionGenerator.setMinSize(minSize);
    collectionGenerator.setMaxSize(maxSize);
    return collectionGenerator;
  }

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator) {
    ContainerGenerator<Collection> collectionGenerator =
        (ContainerGenerator<Collection>) registry.getGenerator(collectionType);
    collectionGenerator.setElementsGenerators(generator);
    return cast(collectionGenerator);
  }

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator, int size) {
    G collectionGenerator = collectionGenerator(collectionType, generator);
    collectionGenerator.setSize(size);
    return collectionGenerator;
  }

  @Override
  public <C, T, G extends ContainerGenerator<C>> G collectionGenerator(Class<C> collectionType,
      Generator<T> generator, int minSize, int maxSize) {
    G collectionGenerator = collectionGenerator(collectionType, generator);
    collectionGenerator.setMinSize(minSize);
    collectionGenerator.setMaxSize(maxSize);
    return collectionGenerator;
  }

  // ---

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType) {
    ContainerGenerator<List> listGenerator =
        (ContainerGenerator<List>) registry.getGenerator(List.class);
    listGenerator.setElementsTypes(elementsType);
    return cast(listGenerator);
  }

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType,
      int size) {
    G listGenerator = listGenerator(elementsType);
    listGenerator.setSize(size);
    return listGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Class<T> elementsType,
      int minSize, int maxSize) {
    G listGenerator = listGenerator(elementsType);
    listGenerator.setMinSize(minSize);
    listGenerator.setMaxSize(maxSize);
    return listGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator) {
    ContainerGenerator<List> listGenerator =
        (ContainerGenerator<List>) registry.getGenerator(List.class);
    listGenerator.setElementsGenerators(generator);
    return cast(listGenerator);
  }

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator,
      int size) {
    G listGenerator = listGenerator(generator);
    listGenerator.setSize(size);
    return listGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<List<T>>> G listGenerator(Generator<T> generator,
      int minSize, int maxSize) {
    G listGenerator = listGenerator(generator);
    listGenerator.setMinSize(minSize);
    listGenerator.setMaxSize(maxSize);
    return listGenerator;
  }

  // ---

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType) {
    ContainerGenerator<Set> setGenerator =
        (ContainerGenerator<Set>) registry.getGenerator(Set.class);
    setGenerator.setElementsTypes(elementsType);
    return cast(setGenerator);
  }

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType, int size) {
    G setGenerator = setGenerator(elementsType);
    setGenerator.setSize(size);
    return setGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Class<T> elementsType,
      int minSize, int maxSize) {
    G setGenerator = setGenerator(elementsType);
    setGenerator.setMinSize(minSize);
    setGenerator.setMaxSize(maxSize);
    return setGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator) {
    ContainerGenerator<Set> setGenerator =
        (ContainerGenerator<Set>) registry.getGenerator(Set.class);
    setGenerator.setElementsGenerators(generator);
    return cast(setGenerator);
  }

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator,
      int size) {
    G setGenerator = setGenerator(generator);
    setGenerator.setSize(size);
    return setGenerator;
  }

  @Override
  public <T, G extends ContainerGenerator<Set<T>>> G setGenerator(Generator<T> generator,
      int minSize, int maxSize) {
    G setGenerator = setGenerator(generator);
    setGenerator.setMinSize(minSize);
    setGenerator.setMaxSize(maxSize);
    return setGenerator;
  }

  // ---

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType) {
    ContainerGenerator<Map> mapGenerator =
        (ContainerGenerator<Map>) registry.getGenerator(Map.class);
    mapGenerator.setElementsTypes(keyType, valueType);
    return cast(mapGenerator);
  }

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType, int size) {
    G mapGenerator = mapGenerator(keyType, valueType);
    mapGenerator.setSize(size);
    return mapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Class<K> keyType,
      Class<V> valueType, int minSize, int maxSize) {
    G mapGenerator = mapGenerator(keyType, valueType);
    mapGenerator.setMinSize(minSize);
    mapGenerator.setMaxSize(maxSize);
    return mapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator) {
    ContainerGenerator<Map> mapGenerator =
        (ContainerGenerator<Map>) registry.getGenerator(Map.class);
    mapGenerator.setElementsGenerators(keyGenerator, valueGenerator);
    return cast(mapGenerator);
  }

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int size) {
    G mapGenerator = mapGenerator(keyGenerator, valueGenerator);
    mapGenerator.setSize(size);
    return mapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<Map<K, V>>> G mapGenerator(Generator<K> keyGenerator,
      Generator<V> valueGenerator, int minSize, int maxSize) {
    G mapGenerator = mapGenerator(keyGenerator, valueGenerator);
    mapGenerator.setMinSize(minSize);
    mapGenerator.setMaxSize(maxSize);
    return mapGenerator;
  }

  // ---

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType) {
    ContainerGenerator<TreeMap> treeMapGenerator =
        (ContainerGenerator<TreeMap>) registry.getGenerator(TreeMap.class);
    treeMapGenerator.setElementsTypes(keyType, valueType);
    return cast(treeMapGenerator);
  }

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType, int size) {
    G treeMapGenerator = treeMapGenerator(keyType, valueType);
    treeMapGenerator.setSize(size);
    return treeMapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(Class<K> keyType,
      Class<V> valueType, int minSize, int maxSize) {
    G treeMapGenerator = treeMapGenerator(keyType, valueType);
    treeMapGenerator.setMinSize(minSize);
    treeMapGenerator.setMaxSize(maxSize);
    return treeMapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(
      Generator<K> keyGenerator, Generator<V> valueGenerator) {
    ContainerGenerator<TreeMap> treeMapGenerator =
        (ContainerGenerator<TreeMap>) registry.getGenerator(TreeMap.class);
    treeMapGenerator.setElementsGenerators(keyGenerator, valueGenerator);
    return cast(treeMapGenerator);
  }

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(
      Generator<K> keyGenerator, Generator<V> valueGenerator, int size) {
    G treeMapGenerator = treeMapGenerator(keyGenerator, valueGenerator);
    treeMapGenerator.setSize(size);
    return treeMapGenerator;
  }

  @Override
  public <K, V, G extends ContainerGenerator<TreeMap<K, V>>> G treeMapGenerator(
      Generator<K> keyGenerator, Generator<V> valueGenerator, int minSize, int maxSize) {
    G treeMapGenerator = treeMapGenerator(keyGenerator, valueGenerator);
    treeMapGenerator.setMinSize(minSize);
    treeMapGenerator.setMaxSize(maxSize);
    return treeMapGenerator;
  }

  // ---

  @Override
  public <E extends Enum<E>> Generator<E> enumGenerator(Class<E> enumClass) {
    return registry.getGenerator(enumClass);
  }

  @Override
  public <T> Generator<T> arrayGenerator(Class<T> arrayClass) {
    return registry.getGenerator(arrayClass);
  }

  @Override
  public <T> Generator<T> elementGenerator(Object container) {
    return new RandomElementGenerator(container);
  }

  @Override
  public RandomGeneratorsRegistry registry() {
    return registry;
  }
}
