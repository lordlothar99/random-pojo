/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigDecimal;

/**
 * Random {@link Short} generator
 * 
 * @author Francois Lecomte
 */
public class RandomShortGenerator extends AbstractRandomNumericGenerator<Short> {

	public RandomShortGenerator() {
		super((short) 0, (short) 100);
	}

	public RandomShortGenerator(Short min, Short max) {
		super(min, max);
	}

	@Override
	protected Short fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.shortValue();
	}
}
