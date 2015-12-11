package com.github.lordlothar99.random.impl.spring.cache;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.github.lordlothar99.random.IRandomGenerators;
import com.github.lordlothar99.random.api.RangedGenerator;

@ContextConfiguration("applicationContext-test-cache.xml")
public class RandomGeneratorsCacheTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  private IRandomGenerators randomGenerators;

  @Test
  public void test() {
    RangedGenerator<BigDecimal> bigDecimalGenerator = randomGenerators.bigDecimalGenerator();
    RangedGenerator<BigDecimal> bigDecimalGenerator2 = randomGenerators.bigDecimalGenerator();
    
    assertTrue("Generators should be exactly the same instances", bigDecimalGenerator == bigDecimalGenerator2);
  }

}
