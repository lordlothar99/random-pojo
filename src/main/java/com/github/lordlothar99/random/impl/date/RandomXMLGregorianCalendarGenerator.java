/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.api.Generator;

/**
 * Generateur aleatoire de {@link XMLGregorianCalendar}.
 * 
 * @author Francois Lecomte
 */
public class RandomXMLGregorianCalendarGenerator implements Generator<XMLGregorianCalendar> {

    /**
     * {@inheritDoc}
     */
    public XMLGregorianCalendar create() {
        try {
            final Calendar calendar = RandomToolkit.CALENDAR.create();
            return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar ) calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
