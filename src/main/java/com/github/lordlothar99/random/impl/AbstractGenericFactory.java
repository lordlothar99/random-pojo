/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.api.IGenericFactory;

/**
 * Implementation abstraite de {@link IGenericFactory}.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public abstract class AbstractGenericFactory<O> extends AbstractFactory<O> implements IGenericFactory<O> {

    /**
     * Type de donnees generique
     */
    private Class< ? >[] genericTypes;

    /**
     * @param objectClass Type de l'objet genere
     */
    public AbstractGenericFactory(Class<O> clazz) {
        super(clazz);
    }

    /**
     * @param objectClass Type de l'objet genere
     * @param genericTypes Types generiques
     */
    public AbstractGenericFactory(Class<O> clazz, Class< ? >... genericTypes) {
        super(clazz);
        this.genericTypes = genericTypes;
    }

    /**
     * @return the genericTypes
     */
    public Class< ? >[] getGenericTypes() {
        return genericTypes;
    }

    /**
     * @param genericTypes the genericTypes to set
     */
    public void setGenericTypes(Class< ? >... genericTypes) {
        this.genericTypes = genericTypes;
    }

}
