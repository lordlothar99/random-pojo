/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

/**
 * Un generateur de donnees supportant les generics.
 * 
 * @author Francois Lecomte
 * @param <O> Type de l'objet genere
 */
public interface IGenericFactory<O> extends Factory<O> {

    /**
     * @param genericTypes Type de donnees generique
     */
    void setGenericTypes(Class< ? >... genericTypes);
}
