/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import org.apache.commons.collections.map.MultiKeyMap;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.impl.AbstractGenericGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;

/**
 * Generateur aleatoire pour les {@link MultiKeyMap}.
 * 
 * @author Francois Lecomte
 */
public class RandomMultiKeyMapGenerator extends AbstractGenericGenerator<MultiKeyMap> {

    /**
     * type des valeurs
     */
    private Class< ? > valueType;

    /**
     * Constructeur
     * 
     * @param clazz
     * @param valueType type des valeurs
     * @param keyTypes types des cles
     */
    public RandomMultiKeyMapGenerator(Class< ? > valueType, Class< ? >... keyTypes) {
        super(MultiKeyMap.class, keyTypes);
        this.valueType = valueType;
    }

    /**
     * {@inheritDoc}
     */
    public MultiKeyMap create() {
        // new instance
        final MultiKeyMap map = new MultiKeyMap();

        // random size
        final RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator(5);
        final int elementsCount = randomIntegerGenerator.create() + 2;

        // random fill
        for (int i = 0; i < elementsCount; i++) {
            if (getGenericTypes().length == 1) {
                map.put(RandomToolkit.get().generate(getGenericTypes()[0]), RandomToolkit.get().generate(valueType));
            } else if (getGenericTypes().length == 2) {
                map.put(RandomToolkit.get().generate(getGenericTypes()[0]), RandomToolkit.get()
                                .generate(getGenericTypes()[1]), RandomToolkit.get().generate(valueType));
            } else if (getGenericTypes().length == 3) {
                map.put(RandomToolkit.get().generate(getGenericTypes()[0]), RandomToolkit.get()
                                .generate(getGenericTypes()[1]), RandomToolkit.get().generate(getGenericTypes()[2]),
                                RandomToolkit.get().generate(valueType));
            } else if (getGenericTypes().length == 4) {
                map.put(RandomToolkit.get().generate(getGenericTypes()[0]), RandomToolkit.get()
                                .generate(getGenericTypes()[1]), RandomToolkit.get().generate(getGenericTypes()[2]),
                                RandomToolkit.get().generate(getGenericTypes()[3]), RandomToolkit.get()
                                                .generate(valueType));
            } else if (getGenericTypes().length == 5) {
                map.put(RandomToolkit.get().generate(getGenericTypes()[0]), RandomToolkit.get()
                                .generate(getGenericTypes()[1]), RandomToolkit.get().generate(getGenericTypes()[2]),
                                RandomToolkit.get().generate(getGenericTypes()[3]), RandomToolkit.get()
                                                .generate(getGenericTypes()[4]), RandomToolkit.get()
                                                .generate(valueType));
            }
        }

        return map;
    }
}
