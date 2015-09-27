/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

/**
 * Implementation abstraite de {@link Factory}.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public abstract class AbstractFactory<O> implements ObjectClassFactory<O> {

    /**
     * {@link Class} de l'objet genere
     */
    private Class<O> objectClass;

    /**
     * Constructeur
     */
    public AbstractFactory() {
        super();
    }

    /**
     * Constructeur
     * 
     * @param objectClass Type de l'objet genere
     */
    public AbstractFactory(Class<O> clazz) {
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
