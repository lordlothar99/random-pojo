/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang.ClassUtils;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.ObjectClassGenerator;
import com.github.lordlothar99.random.impl.RandomEnumGenerator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;
import com.github.lordlothar99.random.impl.RandomStringGenerator;
import com.github.lordlothar99.random.impl.collections.RandomArrayGenerator;
import com.github.lordlothar99.random.impl.collections.RandomCollectionGenerator;
import com.github.lordlothar99.random.impl.collections.RandomMapGenerator;
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

    /**
     * User-defined factories (or factory classes)
     */
    private static final Map<Class< ? >, Object> registry = new HashMap<Class< ? >, Object>();
    static {
        initRegistry();
    }

    private static void initRegistry() {
        registry.put(BigDecimal.class, BIG_DECIMAL);
        registry.put(Boolean.class, BOOLEAN);
        registry.put(boolean.class, BOOLEAN);
        registry.put(Calendar.class, CALENDAR);
        registry.put(Date.class, DATE);
        registry.put(BigInteger.class, BIG_INTEGER);
        registry.put(Integer.class, INTEGER);
        registry.put(int.class, INTEGER);
        registry.put(Long.class, LONG);
        registry.put(long.class, LONG);
        registry.put(Short.class, SHORT);
        registry.put(short.class, SHORT);
        registry.put(Byte.class, BYTE);
        registry.put(byte.class, BYTE);
        registry.put(Double.class, DOUBLE);
        registry.put(double.class, DOUBLE);
        registry.put(Float.class, FLOAT);
        registry.put(float.class, FLOAT);
        registry.put(String.class, STRING);
        try {
            registry.put(Class.forName("org.joda.time.LocalDate"), LOCAL_DATE);
            registry.put(Class.forName("org.joda.time.LocalDateTime"), LOCAL_DATE_TIME);
            registry.put(Class.forName("org.joda.time.DateTime"), DATE_TIME);
        } catch (ClassNotFoundException e) {
            // joda unavailable
        }
        registry.put(XMLGregorianCalendar.class, XMLGREGORIANCALENDAR);

        registry.put(Enum.class, RandomEnumGenerator.class);
        registry.put(Collection.class, RandomCollectionGenerator.class);
        registry.put(Map.class, RandomMapGenerator.class);
        registry.put(Object.class, RandomObjectGenerator.class);
    }

    /**
     * Constructeur
     */
    private RandomToolkit() {
    }

    /**
     * @param <O> Type de la donnee e generer
     * @param type Type de la donnee e generer
     * @return un {@link Generator} pour le type de donnees specifie
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    public static <O> Generator<O> getInstance(Class<O> type) {

        Generator<O> factory = null;

        if (Enum.class.isAssignableFrom(type)) {
            factory = new RandomEnumGenerator(type);
        } else if (type.isArray()) {
            factory = new RandomArrayGenerator(type);
            // } else if (Collection.class.isAssignableFrom(type)) {
            // factory = new RandomCollectionGenerator(type);
            // } else if (Map.class.isAssignableFrom(type)) {
            // factory = new RandomMapGenerator(type);
            // } else {
            // factory = new RandomObjectGenerator<O>(type);
        }

        // exploration par les superclass
        for (Class< ? > theClass = type; factory == null && theClass != null; theClass = theClass.getSuperclass()) {
            factory = getFromRegistry(factory, theClass);
        }

        if (factory instanceof ObjectClassGenerator) {
            ObjectClassGenerator objectClassGenerator = (ObjectClassGenerator ) factory;
            objectClassGenerator.setObjectClass(type);
        }
        return factory;
    }

    /**
     * @param factory
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <O> Generator<O> getFromRegistry(Generator<O> factory, Class< ? > type) {
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
    public static <O> void register(Class<O> type, Generator<O> factory) {
        registry.put(type, factory);
    }

    /**
     * Ajoute une {@link Generator} au registre.
     * 
     * @param type
     * @param factoryClass
     */
    public static <O> void register(Class<O> type, Class< ? > factoryClass) {
        registry.put(type, factoryClass);
    }

    /**
     * @param <O> type
     * @param type {@link Class}
     * @return {@link Object}
     */
    public static <O> O generate(Class<O> type) {
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
    public static Object[] generate(Class< ? >[] types) {

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
