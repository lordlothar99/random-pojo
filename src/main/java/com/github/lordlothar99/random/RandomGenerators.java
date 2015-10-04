package com.github.lordlothar99.random;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.RangedGenerator;
import com.github.lordlothar99.random.impl.RandomStringGenerator;
import com.github.lordlothar99.random.impl.date.RandomCalendarGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomXMLGregorianCalendarGenerator;

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
}
