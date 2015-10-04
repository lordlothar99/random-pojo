/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.date;

import org.joda.time.LocalDateTime;

/**
 * Random {@link LocalDateTime} generator
 * 
 * @author Francois Lecomte
 */
public class RandomLocalDateTimeGenerator extends AbstractRandomDateGenerator<LocalDateTime> {

	@Override
	protected long asLong(LocalDateTime date) {
		return date.toDate().getTime();
	}

	@Override
	protected LocalDateTime fromLong(long timeInMillis) {
		return new LocalDateTime(timeInMillis);
	}
}
