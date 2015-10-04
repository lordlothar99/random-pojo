/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigDecimal;

/**
 * Random {@link Double} generator
 * 
 * @author Francois Lecomte
 */
public class RandomDoubleGenerator extends AbstractRandomNumericGenerator<Double> {

	public RandomDoubleGenerator() {
		super(0d, 1d);
	}

	public RandomDoubleGenerator(Double min, Double max) {
		super(min, max);
	}

	@Override
	protected Double fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.doubleValue();
	}
}
