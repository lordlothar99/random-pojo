/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

import java.math.BigDecimal;

/**
 * Random {@link Integer} generator
 *
 * @author Francois Lecomte
 */
public class RandomIntegerGenerator extends AbstractRandomNumericGenerator<Integer> {

	public RandomIntegerGenerator() {
		super(0, 100);
	}

	public RandomIntegerGenerator(Integer min, Integer max) {
		super(min, max);
	}

	@Override
	protected Integer fromBigDecimal(BigDecimal bigDecimal) {
		bigDecimal = bigDecimal.setScale(0, ROUND_HALF_EVEN);
		return bigDecimal.intValue();
	}
}
