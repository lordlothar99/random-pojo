/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.ObjectClassGenerator;

/**
 * Implementation abstraite de {@link Generator}.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public abstract class AbstractGenerator<O> implements ObjectClassGenerator<O> {

    /**
     * {@link Class} de l'objet genere
     */
    private Class<O> objectClass;

    /**
     * Constructeur
     */
    public AbstractGenerator() {
        super();
    }

    /**
     * Constructeur
     * 
     * @param objectClass Type de l'objet genere
     */
    public AbstractGenerator(Class<O> clazz) {
        super();
        this.objectClass = clazz;
    }

    /**
     * @return the objectClass
     */
    public Class<O> getObjectClass() {
        return objectClass;
    }

    /**
     * @param objectClass the objectClass to set
     */
    public void setObjectClass(Class<O> objectClass) {
        this.objectClass = objectClass;
    }
}
