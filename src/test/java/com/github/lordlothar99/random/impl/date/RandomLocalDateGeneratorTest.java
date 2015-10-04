package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomLocalDateGeneratorTest {

	@Test
	public void should_random_date_be_not_null() {
		RandomLocalDateGenerator generator = new RandomLocalDateGenerator();
		LocalDate result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_dateTime_be_within_range() {
		LocalDate min = new LocalDate(1952, 4, 26);
		LocalDate max = new LocalDate(2012, 3, 12);
		RandomLocalDateGenerator generator = new RandomLocalDateGenerator();
		generator.setMin(min);
		generator.setMax(max);
		LocalDate randomLocalDate = generator.create();
		assertThat("More than " + min, randomLocalDate.toDate().getTime(), new GreaterOrEqual<Long>(min.toDate().getTime()));
		assertThat("Less than " + max, randomLocalDate.toDate().getTime(), new LessOrEqual<Long>(max.toDate().getTime()));
	}
}
