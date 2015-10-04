package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomDateGeneratorTest {

	@Test
	public void should_random_date_be_not_null() {
		RandomDateGenerator generator = new RandomDateGenerator();
		Date result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_date_be_within_range() {
		Calendar cal = Calendar.getInstance();
		cal.set(1952, 4, 26);
		Date min = cal.getTime();
		cal.set(2012, 3, 12);
		Date max = cal.getTime();
		RandomDateGenerator generator = new RandomDateGenerator();
		generator.setMin(min);
		generator.setMax(max);
		Date randomDate = generator.create();
		assertThat("More than " + min, randomDate, new GreaterOrEqual<Date>(min));
		assertThat("Less than " + max, randomDate, new LessOrEqual<Date>(max));
	}
}
