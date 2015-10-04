/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Random {@link XMLGregorianCalendar} generator
 * 
 * @author Francois Lecomte
 */
public class RandomXMLGregorianCalendarGenerator extends AbstractRandomDateGenerator<XMLGregorianCalendar> {

	@Override
	protected long asLong(XMLGregorianCalendar calendar) {
		return calendar.toGregorianCalendar().getTimeInMillis();
	}

	@Override
	protected XMLGregorianCalendar fromLong(long timeInMillis) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timeInMillis);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) calendar);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

}
