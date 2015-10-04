/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

/**
 * Random {@link BigDecimal} generator
 * 
 * @author Francois Lecomte
 */
public class RandomBigDecimalGenerator extends AbstractRandomNumericGenerator<BigDecimal> {

	public RandomBigDecimalGenerator() {
		super(ZERO, new BigDecimal("100"));
	}

	public RandomBigDecimalGenerator(BigDecimal min, BigDecimal max) {
		super(min, max);
	}

	@Override
	protected BigDecimal fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal;
	}

}
