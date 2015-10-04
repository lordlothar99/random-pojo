/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import java.util.Calendar;

/**
 * Random {@link Calendar} generator
 * 
 * @author Francois Lecomte
 */
public class RandomCalendarGenerator extends AbstractRandomDateGenerator<Calendar> {

	public RandomCalendarGenerator(Calendar min, Calendar max) {
		super(min, max, Calendar.class);
	}

	public RandomCalendarGenerator() {
		super(Calendar.class);
	}

	@Override
	protected long asLong(Calendar calendar) {
		return calendar.getTimeInMillis();
	}

	@Override
	protected Calendar fromLong(long timeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		return calendar;
	}

}
