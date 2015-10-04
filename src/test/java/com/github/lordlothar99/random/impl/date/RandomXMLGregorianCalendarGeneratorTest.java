package com.github.lordlothar99.random.impl.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.LessOrEqual;

public class RandomXMLGregorianCalendarGeneratorTest {

	@Test
	public void should_random_xmlGregorianCalendar_be_not_null() {
		RandomXMLGregorianCalendarGenerator generator = new RandomXMLGregorianCalendarGenerator();
		XMLGregorianCalendar result = generator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_xmlGregorianCalendar_be_within_range() throws DatatypeConfigurationException {
		Calendar minCal = Calendar.getInstance();
		minCal.set(1952, 4, 26);
		Calendar maxCal = Calendar.getInstance();
		maxCal.set(2012, 3, 12);
		XMLGregorianCalendar min = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) minCal);
		XMLGregorianCalendar max = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) maxCal);
		RandomXMLGregorianCalendarGenerator generator = new RandomXMLGregorianCalendarGenerator();
		generator.setMin(min);
		generator.setMax(max);
		XMLGregorianCalendar randomXMLGregorianCalendar = generator.create();
		assertThat("More than " + min, randomXMLGregorianCalendar.toGregorianCalendar().getTimeInMillis(),
				new GreaterOrEqual<Long>(min.toGregorianCalendar().getTimeInMillis()));
		assertThat("Less than " + max, randomXMLGregorianCalendar.toGregorianCalendar().getTimeInMillis(),
				new LessOrEqual<Long>(max.toGregorianCalendar().getTimeInMillis()));
	}
}
