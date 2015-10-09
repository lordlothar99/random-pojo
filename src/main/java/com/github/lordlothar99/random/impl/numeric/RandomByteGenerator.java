/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

import java.math.BigDecimal;

/**
 * Random {@link Byte} generator
 *
 * @author Francois Lecomte
 */
public class RandomByteGenerator extends AbstractRandomNumericGenerator<Byte> {

	public RandomByteGenerator() {
		super((byte) 0, (byte) 100);
	}

	public RandomByteGenerator(Byte min, Byte max) {
		super(min, max);
	}

	@Override
	protected Byte fromBigDecimal(BigDecimal bigDecimal) {
		bigDecimal = bigDecimal.setScale(0, ROUND_HALF_EVEN);
		return bigDecimal.byteValue();
	}

}
