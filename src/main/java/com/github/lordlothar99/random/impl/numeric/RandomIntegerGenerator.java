/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

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

	@Override
	protected Integer fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.intValue();
	}
}
