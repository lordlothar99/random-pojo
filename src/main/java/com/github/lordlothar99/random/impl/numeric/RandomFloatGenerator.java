/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigDecimal;

/**
 * Random {@link Float} generator
 * 
 * @author Francois Lecomte
 */
public class RandomFloatGenerator extends AbstractRandomNumericGenerator<Float> {

	public RandomFloatGenerator() {
		super(0f, 1f);
	}

	@Override
	protected Float fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.floatValue();
	}
}
