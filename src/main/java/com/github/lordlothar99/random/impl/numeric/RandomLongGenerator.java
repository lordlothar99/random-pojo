/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

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

	@Override
	protected Long fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.longValue();
	}
}
