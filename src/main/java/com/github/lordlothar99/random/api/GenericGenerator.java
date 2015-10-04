/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.api;

/**
 * Un generateur de donnees supportant les generics.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public interface GenericGenerator<O> extends Generator<O> {

    /**
     * @param genericTypes Type de donnees generique
     */
    void setGenericTypes(Class< ? >... genericTypes);
}
