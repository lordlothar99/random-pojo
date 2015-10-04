package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomCalendarGeneratorTest {

	@Test
	public void should_random_calendar_be_not_null() {
		RandomCalendarGenerator generator = new RandomCalendarGenerator();
		Calendar result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_calendar_be_within_range() {
		Calendar min = Calendar.getInstance();
		min.set(1952, 4, 26);
		Calendar max = Calendar.getInstance();
		max.set(2012, 3, 12);
		RandomCalendarGenerator generator = new RandomCalendarGenerator();
		generator.setMin(min);
		generator.setMax(max);
		Calendar randomCalendar = generator.create();
		assertThat("More than " + min, randomCalendar, new GreaterOrEqual<Calendar>(min));
		assertThat("Less than " + max, randomCalendar, new LessOrEqual<Calendar>(max));
	}
}
