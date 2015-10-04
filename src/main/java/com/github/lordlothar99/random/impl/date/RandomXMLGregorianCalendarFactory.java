/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.github.lordlothar99.random.RandomFactoryToolkit;
import com.github.lordlothar99.random.api.Factory;

/**
 * Generateur aleatoire de {@link XMLGregorianCalendar}.
 * 
 * @author Francois Lecomte
 */
public class RandomXMLGregorianCalendarFactory implements Factory<XMLGregorianCalendar> {

    /**
     * {@inheritDoc}
     */
    public XMLGregorianCalendar create() {
        try {
            final Calendar calendar = RandomFactoryToolkit.CALENDAR.create();
            return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar ) calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
