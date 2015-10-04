/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.util.List;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang.ClassUtils;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.ObjectClassGenerator;
import com.github.lordlothar99.random.impl.RandomEnumGenerator;
import com.github.lordlothar99.random.impl.RandomStringGenerator;
import com.github.lordlothar99.random.impl.collections.RandomArrayGenerator;
import com.github.lordlothar99.random.impl.date.RandomCalendarGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateGenerator;
import com.github.lordlothar99.random.impl.date.RandomLocalDateTimeGenerator;
import com.github.lordlothar99.random.impl.date.RandomXMLGregorianCalendarGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBigDecimalGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBigIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomBooleanGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomByteGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomDoubleGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomFloatGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomLongGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomShortGenerator;

/**
 * Outils pour les {@link Generator} aleatoires.
 * 
 * @author Francois Lecomte
 */
public final class RandomToolkit {

    /**
     * {@link RandomBigDecimalGenerator}
     */
    public static final RandomBigDecimalGenerator BIG_DECIMAL = new RandomBigDecimalGenerator();

    /**
     * {@link RandomBooleanGenerator}
     */
    public static final RandomBooleanGenerator BOOLEAN = new RandomBooleanGenerator();

    /**
     * {@link RandomCalendarGenerator}
     */
    public static final RandomCalendarGenerator CALENDAR = new RandomCalendarGenerator();

    /**
     * {@link RandomDateGenerator}
     */
    public static final RandomDateGenerator DATE = new RandomDateGenerator();

    /**
     * {@link RandomBigIntegerGenerator}
     */
    public static final RandomBigIntegerGenerator BIG_INTEGER = new RandomBigIntegerGenerator();

    /**
     * {@link RandomIntegerGenerator}
     */
    public static final RandomIntegerGenerator INTEGER = new RandomIntegerGenerator();

    /**
     * {@link RandomLongGenerator}
     */
    public static final RandomLongGenerator LONG = new RandomLongGenerator();

    /**
     * {@link RandomShortGenerator}
     */
    public static final RandomShortGenerator SHORT = new RandomShortGenerator();

    /**
     * {@link RandomByteGenerator}
     */
    public static final RandomByteGenerator BYTE = new RandomByteGenerator();

    /**
     * {@link RandomFloatGenerator}
     */
    public static final RandomFloatGenerator FLOAT = new RandomFloatGenerator();

    /**
     * {@link RandomDoubleGenerator}
     */
    public static final RandomDoubleGenerator DOUBLE = new RandomDoubleGenerator();

    /**
     * {@link RandomStringGenerator}
     */
    public static final RandomStringGenerator STRING = new RandomStringGenerator();

    /**
     * {@link RandomLocalDateGenerator}
     */
    public static final RandomLocalDateGenerator LOCAL_DATE = new RandomLocalDateGenerator();

    /**
     * {@link RandomLocalDateTimeGenerator}
     */
    public static final RandomLocalDateTimeGenerator LOCAL_DATE_TIME = new RandomLocalDateTimeGenerator();

    /**
     * {@link RandomDateTimeGenerator}
     */
    public static final RandomDateTimeGenerator DATE_TIME = new RandomDateTimeGenerator();

    /**
     * {@link RandomXMLGregorianCalendarGenerator}
     */
    public static final RandomXMLGregorianCalendarGenerator XMLGREGORIANCALENDAR =
                    new RandomXMLGregorianCalendarGenerator();

	private static final RandomToolkit INSTANCE = new RandomToolkit();

	private RandomGenerators registry = new RandomGenerators();

    public RandomToolkit() {
    }

    public static RandomToolkit get() {
    	return INSTANCE;
    }
    
    /**
     * @param <O> Type de la donnee e generer
     * @param type Type de la donnee e generer
     * @return un {@link Generator} pour le type de donnees specifie
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    public <O> Generator<O> getInstance(Class<O> type) {

        Generator<O> generator = null;

        if (Enum.class.isAssignableFrom(type)) {
            generator = new RandomEnumGenerator(type);
        } else if (type.isArray()) {
            generator = new RandomArrayGenerator(type);
            // } else if (Collection.class.isAssignableFrom(type)) {
            // factory = new RandomCollectionGenerator(type);
            // } else if (Map.class.isAssignableFrom(type)) {
            // factory = new RandomMapGenerator(type);
            // } else {
            // factory = new RandomObjectGenerator<O>(type);
        }

        // exploration par les superclass
        for (Class< ? > theClass = type; generator == null && theClass != null; theClass = theClass.getSuperclass()) {
            generator = getFromRegistry(generator, theClass);
        }

        if (generator instanceof ObjectClassGenerator) {
            ObjectClassGenerator objectClassGenerator = (ObjectClassGenerator ) generator;
            objectClassGenerator.setObjectClass(type);
        }
        return generator;
    }

    /**
     * @param factory
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    private <O> Generator<O> getFromRegistry(Generator<O> factory, Class< ? > type) {
        int i = 0;
        List<Class< ? >> interfaces = ClassUtils.getAllInterfaces(type);
        // exploration par les interfaces
        Class< ? > theClass = type;
        do {
            Object registeredObject = registry.get(theClass);
            if (registeredObject instanceof Generator) {
                factory = (Generator<O> ) registeredObject;
            } else if (registeredObject != null) {
                Class<O> factoryClass = (Class<O> ) registeredObject;
                try {
                    try {
                        factory = (Generator<O> ) ConstructorUtils.invokeConstructor(factoryClass, type);
                    } catch (NoSuchMethodException e) {
                        factory = (Generator<O> ) ConstructorUtils.invokeConstructor(factoryClass, null);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (factory == null && theClass != null && i < interfaces.size()) {
                theClass = interfaces.get(i);
                i++;
            }
        } while (factory == null && theClass != null && i < interfaces.size());
        return factory;
    }

    /**
     * Ajoute une {@link Generator} au registre.
     * 
     * @param type
     * @param factory
     */
    public <O> void register(Class<O> type, Generator<O> factory) {
        registry.put(type, factory);
    }

    /**
     * Ajoute une {@link Generator} au registre.
     * 
     * @param type
     * @param factoryClass
     */
    public <O> void register(Class<O> type, Class< ? > factoryClass) {
        registry.put(type, factoryClass);
    }

    /**
     * @param <O> type
     * @param type {@link Class}
     * @return {@link Object}
     */
    public <O> O generate(Class<O> type) {
        try {
            final Generator<O> generateur = getInstance(type);
            return generateur.create();
        } catch (final RuntimeException e) {
            throw new RuntimeException("Erreur lors de la generation de l'objet de type '" + type.getName() + "'", e);
        }
    }

    /**
     * @param types tableau de {@link Class}
     * @return tableau d'{@link Object}
     */
    public Object[] generate(Class< ? >[] types) {

        final Object[] values = new Object[types.length];

        // initialisation de tous les parametres
        for (int i = 0; i < values.length; i++) {
            final Class< ? > parametreType = types[i];

            final Generator< ? > generateur = getInstance(parametreType);
            try {
                final Object value = generateur.create();
                values[i] = value;
            } catch (final RuntimeException e) {
                throw new RuntimeException("Erreur lors de la generation de l'objet " + (i + 1) + " de type "
                                + types[i], e);
            }
        }
        return values;
    }
}
