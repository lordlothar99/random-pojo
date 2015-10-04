package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.xml.datatype.DatatypeConfigurationException;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomLocalDateTimeGeneratorTest {

	@Test
	public void should_random_localDateTime_be_not_null() {
		RandomLocalDateTimeGenerator generator = new RandomLocalDateTimeGenerator();
		LocalDateTime result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_localDateTime_be_within_range() throws DatatypeConfigurationException {
		LocalDateTime min = new LocalDateTime(1952, 4, 26, 15, 35, 6);
		LocalDateTime max = new LocalDateTime(2012, 3, 12, 8, 22, 43);
		RandomLocalDateTimeGenerator generator = new RandomLocalDateTimeGenerator();
		generator.setMin(min);
		generator.setMax(max);
		LocalDateTime randomDateTime = generator.create();
		assertThat("More than " + min, randomDateTime.toDate().getTime(), new GreaterOrEqual<Long>(min.toDate().getTime()));
		assertThat("Less than " + max, randomDateTime.toDate().getTime(), new LessOrEqual<Long>(max.toDate().getTime()));
	}
}
