/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import org.apache.commons.collections.map.MultiKeyMap;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.impl.AbstractContainerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;

/**
 * Generateur aleatoire pour les {@link MultiKeyMap}.
 * 
 * @author Francois Lecomte
 */
public class RandomMultiKeyMapGenerator extends AbstractContainerGenerator<MultiKeyMap> {

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
            if (getElementTypes().length == 1) {
                map.put(RandomToolkit.get().generate(getElementTypes()[0]), RandomToolkit.get().generate(valueType));
            } else if (getElementTypes().length == 2) {
                map.put(RandomToolkit.get().generate(getElementTypes()[0]), RandomToolkit.get()
                                .generate(getElementTypes()[1]), RandomToolkit.get().generate(valueType));
            } else if (getElementTypes().length == 3) {
                map.put(RandomToolkit.get().generate(getElementTypes()[0]), RandomToolkit.get()
                                .generate(getElementTypes()[1]), RandomToolkit.get().generate(getElementTypes()[2]),
                                RandomToolkit.get().generate(valueType));
            } else if (getElementTypes().length == 4) {
                map.put(RandomToolkit.get().generate(getElementTypes()[0]), RandomToolkit.get()
                                .generate(getElementTypes()[1]), RandomToolkit.get().generate(getElementTypes()[2]),
                                RandomToolkit.get().generate(getElementTypes()[3]), RandomToolkit.get()
                                                .generate(valueType));
            } else if (getElementTypes().length == 5) {
                map.put(RandomToolkit.get().generate(getElementTypes()[0]), RandomToolkit.get()
                                .generate(getElementTypes()[1]), RandomToolkit.get().generate(getElementTypes()[2]),
                                RandomToolkit.get().generate(getElementTypes()[3]), RandomToolkit.get()
                                                .generate(getElementTypes()[4]), RandomToolkit.get()
                                                .generate(valueType));
            }
        }

        return map;
    }
}
