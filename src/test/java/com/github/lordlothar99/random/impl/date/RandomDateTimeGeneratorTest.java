package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomDateTimeGeneratorTest {

	@Test
	public void should_random_date_be_not_null() {
		RandomDateTimeGenerator generator = new RandomDateTimeGenerator();
		DateTime result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_dateTime_be_within_range() {
		DateTime min = new DateTime(1952, 4, 26, 15, 35, 6);
		DateTime max = new DateTime(2012, 3, 12, 8, 22, 43);
		RandomDateTimeGenerator generator = new RandomDateTimeGenerator();
		generator.setMin(min);
		generator.setMax(max);
		DateTime randomDateTime = generator.create();
		assertThat("More than " + min, randomDateTime.getMillis(), new GreaterOrEqual<Long>(min.getMillis()));
		assertThat("Less than " + max, randomDateTime.getMillis(), new LessOrEqual<Long>(max.getMillis()));
	}
}
