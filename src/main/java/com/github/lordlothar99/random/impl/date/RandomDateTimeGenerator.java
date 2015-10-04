/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.joda.time.DateTime;

/**
 * Random {@link DateTime} generator
 * 
 * @author Francois Lecomte
 */
public class RandomDateTimeGenerator extends AbstractRandomDateGenerator<DateTime> {

	@Override
	protected long asLong(DateTime date) {
		return date.getMillis();
	}

	@Override
	protected DateTime fromLong(long timeInMillis) {
		return new DateTime(timeInMillis);
	}
}
