/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.api.GenericGenerator;

/**
 * Implementation abstraite de {@link GenericGenerator}.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public abstract class AbstractGenericGenerator<O> extends AbstractGenerator<O> implements GenericGenerator<O> {

    /**
     * Type de donnees generique
     */
    private Class< ? >[] genericTypes;

    /**
     * @param objectClass Type de l'objet genere
     */
    public AbstractGenericGenerator(Class<O> clazz) {
        super(clazz);
    }

    /**
     * @param objectClass Type de l'objet genere
     * @param genericTypes Types generiques
     */
    public AbstractGenericGenerator(Class<O> clazz, Class< ? >... genericTypes) {
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
