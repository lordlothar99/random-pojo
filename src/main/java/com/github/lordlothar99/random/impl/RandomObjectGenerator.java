/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.reflect.FieldUtils;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.GenericGenerator;

/**
 * Generateur aleatoire d'objets.
 * 
 * @author Francois Lecomte
 * @param <O> {@link Object}
 */
public class RandomObjectGenerator<O> extends AbstractGenerator<O> {

    /**
     * {@link Generator}
     */
    private Generator<O> generator;

    /**
     * recursive
     */
    private boolean recursive;

    /**
     * Generation stack (prevent from {@link StackOverflowError})
     */
    private static final ThreadLocal<Map<Class< ? >, Object>> GENERATED_OBJECTS =
                    new ThreadLocal<Map<Class< ? >, Object>>();

    /**
     * Constructeur
     * 
     * @param clazz {@link Class}
     */
    public RandomObjectGenerator(Class<O> clazz) {
        this(clazz, true);
    }

    /**
     * Constructeur
     * 
     * @param clazz {@link Class}
     * @param generator {@link Generator}
     */
    public RandomObjectGenerator(Class<O> clazz, Generator<O> factory) {
        this(clazz, factory, true);
    }

    /**
     * Constructeur
     * 
     * @param clazz {@link Class}
     * @param recursive recursive
     */
    public RandomObjectGenerator(Class<O> clazz, boolean recursive) {
        super(clazz);
        this.recursive = recursive;
    }

    /**
     * Constructeur
     * 
     * @param generator {@link Generator}
     * @param recursive recursive
     */
    public RandomObjectGenerator(Class<O> clazz, Generator<O> factory, boolean recursive) {
        this(clazz, recursive);
        this.generator = factory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public O create() {
        // recursive check
        Class<O> objectClass = getObjectClass();
        Map<Class< ? >, Object> generatedObjects = GENERATED_OBJECTS.get();
        O generatedObject;
        if (generatedObjects != null) {
            generatedObject = (O ) generatedObjects.get(objectClass);
            if (generatedObject != null) {
                return generatedObject;
            }
        }
        // generate a new object
        generatedObject = newInstance();

        // add this object to stack
        if (generatedObjects == null) {
            generatedObjects = new HashMap<Class< ? >, Object>();
            GENERATED_OBJECTS.set(generatedObjects);
        }
        generatedObjects.put(objectClass, generatedObject);

        try {
            // recuperation de tous les champs de l'objet
            final Field[] fields = getAllFields(generatedObject);
            for (final Field field : fields) {
                final Class< ? > propertyClass = field.getType();

                // pour eviter les boucles infinies : on n'initialise pas les
                // proprietes du meme type que le parent
                if (ObjectUtils.equals(propertyClass, objectClass)) {
                    continue;
                }

                // generation
                generate(generatedObject, field);
            }
        } finally {
            // remove this object to stack
            generatedObjects.remove(objectClass);
            if (generatedObjects.isEmpty()) {
                GENERATED_OBJECTS.set(null);
            }
        }

        return generatedObject;
    }

    /**
     * Generation de la valeur d'un champ.
     * 
     * @param object {@link Object}
     * @param field {@link Field}
     */
    protected void generate(Object object, Field field) {
        if (Modifier.isFinal(field.getModifiers())) {
            // impossible d'initialiser un champ final
            return;
        }

        // on recupere un generateur pour la propriete
        final Generator< ? > generateur = getGenerateur(field);

        if (!recursive && generateur instanceof RandomObjectGenerator< ? >) {
            // pas de generation des objets imbriques
            return;
        }

        // on genere une valeur aleatoire
        final Object propertyValue = generateur.create();

        Exception exception = null;
        try {
            // on initialise le champ
            FieldUtils.writeField(field, object, propertyValue, true);
        } catch (final IllegalAccessException e) {
            exception = e;
        } catch (final IllegalArgumentException e) {
            exception = e;
        }

        if (exception != null) {
            // on essaie par le writer s'il existe
            try {
                PropertyUtils.setProperty(object, field.getName(), propertyValue);
            } catch (final IllegalAccessException e1) {
                throw new RuntimeException(exception.getMessage(), e1);
            } catch (final InvocationTargetException e1) {
                throw new RuntimeException(exception.getMessage(), e1);
            } catch (final NoSuchMethodException e1) {
                throw new RuntimeException(exception.getMessage(), e1);
            }
        }
    }

    /**
     * @param field {@link Field}
     * @return un {@link Generator} pour le champs <code>field</code>
     */
    protected Generator< ? > getGenerateur(Field field) {
        final Generator< ? > generateur = RandomToolkit.get().getInstance(field.getType());
        if (generateur instanceof GenericGenerator< ? >) {
            final Class< ? >[] genericTypes = getGenericTypes(field);
            ((GenericGenerator< ? > ) generateur).setGenericTypes(genericTypes);
        }
        return generateur;
    }

    /**
     * @return une nouvelle instance
     */
    @SuppressWarnings({"rawtypes", "unchecked" })
    protected O newInstance() {
        O instance = null;
        if (this.generator != null) {
            instance = this.generator.create();
        }
        Exception lastEx = null;
        if (instance == null) {
            try {
                return this.getObjectClass().newInstance();
            } catch (final InstantiationException e) {
                // no op
                lastEx = e;
            } catch (final IllegalAccessException e) {
                // no op
                lastEx = e;
            }
        }

        if (instance == null) {
            final Constructor[] constructors = this.getObjectClass().getConstructors();
            for (final Constructor constructor : constructors) {

                final Class< ? >[] parametreTypes = constructor.getParameterTypes();
                final Object[] parametres = RandomToolkit.get().generate(parametreTypes);

                try {
                    instance = (O ) constructor.newInstance(parametres);
                    break;
                } catch (final IllegalArgumentException e) {
                    // no op
                    lastEx = e;
                } catch (final InstantiationException e) {
                    // no op
                    lastEx = e;
                } catch (final IllegalAccessException e) {
                    // no op
                    lastEx = e;
                } catch (final InvocationTargetException e) {
                    // no op
                    lastEx = e;
                }
            }
        }

        if (instance == null) {
            throw new RuntimeException("Impossible d'instancier un objet de type '" + getObjectClass().getName() + "'",
                            lastEx);
        }
        return instance;
    }

    /**
     * Types generiques
     * 
     * @param field {@link Field}
     * @return tableau de {@link Class}
     */
    protected Class< ? >[] getGenericTypes(Field field) {
        if (!(field.getGenericType() instanceof ParameterizedType)) {
            return null;
        }
        Type[] types = ((ParameterizedType ) field.getGenericType()).getActualTypeArguments();
        if (types == null) {
            return null;
        }
        Class< ? >[] classes = new Class[types.length];
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            classes[i] = (Class< ? > ) type;
        }
        return classes;
    }

    /**
     * @param bean {@link Object}
     * @return Tous les {@link Field} de l'objet, y compris ceux des classes
     *         meres.
     */
    private static Field[] getAllFields(Object bean) {
        final Class< ? extends Object> beanClass = bean.getClass();
        return getAllFields(beanClass);
    }

    /**
     * @param beanClass {@link Class}
     * @return Tous les {@link Field} de la classe, y compris ceux des classes
     *         meres.
     */
    private static Field[] getAllFields(Class< ? extends Object> beanClass) {
        final Field[] fields = beanClass.getDeclaredFields();
        final Class< ? > superclass = beanClass.getSuperclass();
        if (superclass != null) {
            return (Field[] ) ArrayUtils.addAll(fields, getAllFields(superclass));
        } else {
            return fields;
        }
    }
}
