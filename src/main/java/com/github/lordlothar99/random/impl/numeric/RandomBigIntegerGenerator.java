/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.math.BigInteger.ZERO;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Random {@link BigInteger} generator
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerGenerator extends AbstractRandomNumericGenerator<BigInteger> {

	public RandomBigIntegerGenerator() {
		super(ZERO, new BigInteger("100"));
	}

	public RandomBigIntegerGenerator(BigInteger min, BigInteger max) {
		super(min, max);
	}

	@Override
	protected BigInteger fromBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.toBigInteger();
	}

}
