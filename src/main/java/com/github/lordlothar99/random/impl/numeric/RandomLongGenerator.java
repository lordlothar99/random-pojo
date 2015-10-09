/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

import java.math.BigDecimal;

/**
 * Random {@link Long} generator
 *
 * @author Francois Lecomte
 */
public class RandomLongGenerator extends AbstractRandomNumericGenerator<Long> {

	public RandomLongGenerator() {
		super(0L, 100L);
	}

	public RandomLongGenerator(Long min, Long max) {
		super(min, max);
	}

	@Override
	protected Long fromBigDecimal(BigDecimal bigDecimal) {
		bigDecimal = bigDecimal.setScale(0, ROUND_HALF_EVEN);
		return bigDecimal.longValue();
	}
}
