/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Random {@link Date} generator
 * 
 * @author Francois Lecomte
 */
public class RandomDateGenerator extends AbstractRandomDateGenerator<Date> {

	public RandomDateGenerator() {
		super();
	}

	public RandomDateGenerator(Date min, Date max) {
		super(min, max);
	}

	@Override
	protected long asLong(Date date) {
		return date.getTime();
	}

	@Override
	protected Date fromLong(long timeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		return calendar.getTime();
	}

}
