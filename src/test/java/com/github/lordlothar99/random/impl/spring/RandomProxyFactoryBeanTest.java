/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.MyInterface;

/**
 * @author Francois Lecomte
 */
@ContextConfiguration("applicationContext-test-random.xml")
public class RandomProxyFactoryBeanTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "bean")
    private MyInterface testBean;

    /**
     * Test method for {@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}.
     */
    @Test
    public void testBoolean() {
        Boolean referenceBoolean = testBean.testBoolean();
        Assert.assertEquals("Varying boolean value", referenceBoolean, testBean.testBoolean());
        Assert.assertEquals("Varying boolean value", referenceBoolean, testBean.testBoolean());
        Assert.assertEquals("Varying boolean value", referenceBoolean, testBean.testBoolean());
    }

    /**
     * Test method for
     * {@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}
     * .
     */
    @Test
    public void testFoo() {
        Foo referenceFoo = testBean.testFoo();
        Assert.assertEquals("Varying Foo value", referenceFoo, testBean.testFoo());
        Assert.assertEquals("Varying Foo value", referenceFoo, testBean.testFoo());
        Assert.assertEquals("Varying Foo value", referenceFoo, testBean.testFoo());
    }

    /**
     * Test method for
     * {@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}
     * .
     */
    @Test
    public void testList() {
        List<Integer> referenceList = testBean.testList();
        Assert.assertEquals("Varying List value", referenceList, testBean.testList());
        Assert.assertEquals("Varying List value", referenceList, testBean.testList());
        Assert.assertEquals("Varying List value", referenceList, testBean.testList());
    }

    /**
     * Test method for
     * {@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}
     * .
     */
    @Test
    public void testString() {
        String referenceString = testBean.testString();
        Assert.assertEquals("Varying String value", referenceString, testBean.testString());
        Assert.assertEquals("Varying String value", referenceString, testBean.testString());
        Assert.assertEquals("Varying String value", referenceString, testBean.testString());
    }

    /**
     * Test method for
     * {@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}
     * .
     */
    @Test
    public void testVoid() {
        testBean.testVoid();
    }

}
