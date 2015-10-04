/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Boolean} generator
 * 
 * @author Francois Lecomte
 */
public class RandomBooleanGenerator implements Generator<Boolean> {

	public Boolean create() {
		return RandomUtils.nextBoolean();
	}

}
