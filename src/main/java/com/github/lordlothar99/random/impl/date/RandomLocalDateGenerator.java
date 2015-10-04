/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.joda.time.LocalDate;

/**
 * Random {@link LocalDate} generator
 * 
 * @author Francois Lecomte
 */
public class RandomLocalDateGenerator extends AbstractRandomDateGenerator<LocalDate> {

	@Override
	protected long asLong(LocalDate date) {
		return date.toDate().getTime();
	}

	@Override
	protected LocalDate fromLong(long timeInMillis) {
		return new LocalDate(timeInMillis);
	}

}
