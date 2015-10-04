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

import com.github.lordlothar99.random.api.Factory;
import com.github.lordlothar99.random.api.ObjectClassFactory;
import com.github.lordlothar99.random.impl.RandomEnumFactory;
import com.github.lordlothar99.random.impl.RandomObjectFactory;
import com.github.lordlothar99.random.impl.RandomStringFactory;
import com.github.lordlothar99.random.impl.collections.RandomArrayFactory;
import com.github.lordlothar99.random.impl.collections.RandomCollectionFactory;
import com.github.lordlothar99.random.impl.collections.RandomMapFactory;
import com.github.lordlothar99.random.impl.date.RandomCalendarFactory;
import com.github.lordlothar99.random.impl.date.RandomDateFactory;
import com.github.lordlothar99.random.impl.date.RandomDateTimeFactory;
import com.github.lordlothar99.random.impl.date.RandomLocalDateFactory;
import com.github.lordlothar99.random.impl.date.RandomLocalDateTimeFactory;
import com.github.lordlothar99.random.impl.date.RandomXMLGregorianCalendarFactory;
import com.github.lordlothar99.random.impl.numeric.RandomBigDecimalFactory;
import com.github.lordlothar99.random.impl.numeric.RandomBigIntegerFactory;
import com.github.lordlothar99.random.impl.numeric.RandomBooleanFactory;
import com.github.lordlothar99.random.impl.numeric.RandomByteFactory;
import com.github.lordlothar99.random.impl.numeric.RandomDoubleFactory;
import com.github.lordlothar99.random.impl.numeric.RandomFloatFactory;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerFactory;
import com.github.lordlothar99.random.impl.numeric.RandomLongFactory;
import com.github.lordlothar99.random.impl.numeric.RandomShortFactory;

/**
 * Outils pour les {@link Factory} aleatoires.
 * 
 * @author Francois Lecomte
 */
public final class RandomFactoryToolkit {

    /**
     * {@link RandomBigDecimalFactory}
     */
    public static final RandomBigDecimalFactory BIG_DECIMAL = new RandomBigDecimalFactory();

    /**
     * {@link RandomBooleanFactory}
     */
    public static final RandomBooleanFactory BOOLEAN = new RandomBooleanFactory();

    /**
     * {@link RandomCalendarFactory}
     */
    public static final RandomCalendarFactory CALENDAR = new RandomCalendarFactory();

    /**
     * {@link RandomDateFactory}
     */
    public static final RandomDateFactory DATE = new RandomDateFactory();

    /**
     * {@link RandomBigIntegerFactory}
     */
    public static final RandomBigIntegerFactory BIG_INTEGER = new RandomBigIntegerFactory();

    /**
     * {@link RandomIntegerFactory}
     */
    public static final RandomIntegerFactory INTEGER = new RandomIntegerFactory();

    /**
     * {@link RandomLongFactory}
     */
    public static final RandomLongFactory LONG = new RandomLongFactory();

    /**
     * {@link RandomShortFactory}
     */
    public static final RandomShortFactory SHORT = new RandomShortFactory();

    /**
     * {@link RandomByteFactory}
     */
    public static final RandomByteFactory BYTE = new RandomByteFactory();

    /**
     * {@link RandomFloatFactory}
     */
    public static final RandomFloatFactory FLOAT = new RandomFloatFactory();

    /**
     * {@link RandomDoubleFactory}
     */
    public static final RandomDoubleFactory DOUBLE = new RandomDoubleFactory();

    /**
     * {@link RandomStringFactory}
     */
    public static final RandomStringFactory STRING = new RandomStringFactory();

    /**
     * {@link RandomLocalDateFactory}
     */
    public static final RandomLocalDateFactory LOCAL_DATE = new RandomLocalDateFactory();

    /**
     * {@link RandomLocalDateTimeFactory}
     */
    public static final RandomLocalDateTimeFactory LOCAL_DATE_TIME = new RandomLocalDateTimeFactory();

    /**
     * {@link RandomDateTimeFactory}
     */
    public static final RandomDateTimeFactory DATE_TIME = new RandomDateTimeFactory();

    /**
     * {@link RandomXMLGregorianCalendarFactory}
     */
    public static final RandomXMLGregorianCalendarFactory XMLGREGORIANCALENDAR =
                    new RandomXMLGregorianCalendarFactory();

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

        registry.put(Enum.class, RandomEnumFactory.class);
        registry.put(Collection.class, RandomCollectionFactory.class);
        registry.put(Map.class, RandomMapFactory.class);
        registry.put(Object.class, RandomObjectFactory.class);
    }

    /**
     * Constructeur
     */
    private RandomFactoryToolkit() {
    }

    /**
     * @param <O> Type de la donnee e generer
     * @param type Type de la donnee e generer
     * @return un {@link Factory} pour le type de donnees specifie
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    public static <O> Factory<O> getInstance(Class<O> type) {

        Factory<O> factory = null;

        if (Enum.class.isAssignableFrom(type)) {
            factory = new RandomEnumFactory(type);
        } else if (type.isArray()) {
            factory = new RandomArrayFactory(type);
            // } else if (Collection.class.isAssignableFrom(type)) {
            // factory = new RandomCollectionFactory(type);
            // } else if (Map.class.isAssignableFrom(type)) {
            // factory = new RandomMapFactory(type);
            // } else {
            // factory = new RandomObjectFactory<O>(type);
        }

        // exploration par les superclass
        for (Class< ? > theClass = type; factory == null && theClass != null; theClass = theClass.getSuperclass()) {
            factory = getFromRegistry(factory, theClass);
        }

        if (factory instanceof ObjectClassFactory) {
            ObjectClassFactory objectClassFactory = (ObjectClassFactory ) factory;
            objectClassFactory.setObjectClass(type);
        }
        return factory;
    }

    /**
     * @param factory
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <O> Factory<O> getFromRegistry(Factory<O> factory, Class< ? > type) {
        int i = 0;
        List<Class< ? >> interfaces = ClassUtils.getAllInterfaces(type);
        // exploration par les interfaces
        Class< ? > theClass = type;
        do {
            Object registeredObject = registry.get(theClass);
            if (registeredObject instanceof Factory) {
                factory = (Factory<O> ) registeredObject;
            } else if (registeredObject != null) {
                Class<O> factoryClass = (Class<O> ) registeredObject;
                try {
                    try {
                        factory = (Factory<O> ) ConstructorUtils.invokeConstructor(factoryClass, type);
                    } catch (NoSuchMethodException e) {
                        factory = (Factory<O> ) ConstructorUtils.invokeConstructor(factoryClass, null);
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
     * Ajoute une {@link Factory} au registre.
     * 
     * @param type
     * @param factory
     */
    public static <O> void register(Class<O> type, Factory<O> factory) {
        registry.put(type, factory);
    }

    /**
     * Ajoute une {@link Factory} au registre.
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
            final Factory<O> generateur = getInstance(type);
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

            final Factory< ? > generateur = getInstance(parametreType);
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
