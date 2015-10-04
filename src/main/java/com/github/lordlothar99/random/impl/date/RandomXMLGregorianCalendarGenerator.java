/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.github.lordlothar99.random.RandomGeneratorsRegistry;
import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link XMLGregorianCalendar} generator
 * 
 * @author Francois Lecomte
 */
public class RandomXMLGregorianCalendarGenerator implements Generator<XMLGregorianCalendar> {

    public XMLGregorianCalendar create() {
        try {
        	RandomGeneratorsRegistry registry = new RandomGeneratorsRegistry();
            final Calendar calendar = registry.getGenerator(Calendar.class).create();
            return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar ) calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
