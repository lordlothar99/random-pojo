/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.Assert;

/**
 * Factory Spring pour generer des objets aleatoires
 * 
 * @author Francois Lecomte
 */
public class RandomProxyFactoryBean<T> extends AbstractFactoryBean<T> {

    /**
     * Le type des beans e instancier
     */
    private Class< ? > objectType;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected T createInstance() throws Exception {
        Assert.notNull(getObjectType(), "objectType obligatoire");

        final InvocationHandler invocationHandler = new RandomInvocationHandler();

        // instantiate proxy
        final T proxy =
                        (T ) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{objectType },
                                        invocationHandler);
        return proxy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class< ? > getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    @Required
    public void setObjectType(Class< ? > objectType) {
        this.objectType = objectType;
    }

}
