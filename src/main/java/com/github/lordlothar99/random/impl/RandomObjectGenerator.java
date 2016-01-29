/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static java.lang.reflect.Modifier.isFinal;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;
import static org.apache.commons.lang.ArrayUtils.addAll;
import static org.apache.commons.lang.ArrayUtils.contains;
import static org.apache.commons.lang.reflect.FieldUtils.readField;
import static org.apache.commons.lang.reflect.FieldUtils.writeField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Object} generator
 *
 * @author Francois Lecomte
 * @param <T>
 *            {@link Object}
 */
public class RandomObjectGenerator<T> extends AbstractGenerator<T> {

	/**
	 * Generate fields handled by a {@link Generator<Object>}
	 */
	private boolean generateObjectFields;

	/**
	 * Generation stack (prevent from {@link StackOverflowError})
	 */
	private static final ThreadLocal<Map<Class<?>, Object>> GENERATED_OBJECTS = new ThreadLocal<Map<Class<?>, Object>>();

	private Map<String, Generator<?>> fieldGenerators = new HashMap<String, Generator<?>>();

	private List<String> skippedFields = new ArrayList<String>();

	private String[] forcedFields;

	public RandomObjectGenerator(Class<T> objectClass) {
		this(objectClass, true);
	}

	public RandomObjectGenerator(Class<T> objectClass, boolean generateObjectFields) {
		super(objectClass);
		this.generateObjectFields = generateObjectFields;
	}

	@SuppressWarnings("unchecked")
	public T create() {
		// generateObjectFields check
		Class<? extends T> objectClass = getObjectClass();
		Map<Class<?>, Object> generatedObjects = GENERATED_OBJECTS.get();
		T generatedObject;
		if (generatedObjects != null) {
			generatedObject = (T) generatedObjects.get(objectClass);
			if (generatedObject != null) {
				return generatedObject;
			}
		}
		// generate a new object
		generatedObject = newInstance();

		// add this object to stack
		if (generatedObjects == null) {
			generatedObjects = new HashMap<Class<?>, Object>();
			GENERATED_OBJECTS.set(generatedObjects);
		}
		generatedObjects.put(objectClass, generatedObject);

		try {
			// recuperation de tous les champs de l'objet
			final Field[] fields = getAllFields(generatedObject);
			for (final Field field : fields) {
				final Class<?> propertyClass = field.getType();

				// pour eviter les boucles infinies : on n'initialise pas les
				// proprietes du meme type que le parent
				if (ObjectUtils.equals(propertyClass, objectClass)) {
					logger.debug("Field '{}' skipped because of parent-child identical class : {}", field.getName(),
							propertyClass.getName());
					continue;
				}

				// generation
				generateFieldValue(generatedObject, field);
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

	protected void generateFieldValue(Object object, Field field) {

		if (skippedFields.contains(field.getName())) {
			logger.debug("Field '{}' skipped", field.getName());
			return;
		}

		if (isFinal(field.getModifiers())) {
			logger.debug("Unable to generate field '{}' cause it is final", field.getName());
			return;
		}

		if (!contains(forcedFields, field.getName()) && shouldSkipField(field, object)) {
            logger.debug("Skipping field '{}' cause it already has a value and it's not forced", field.getName());
            return;
        }

		// on recupere un generateur pour la propriete
		final Generator<?> generator = getGenerator(field);

		if (generator == null) {
			logger.debug("Skipping field '{}' cause no generator found", field.getName());
			return;
		}

		if (!generateObjectFields && generator instanceof RandomObjectGenerator<?>) {
			logger.debug("Skipping field '{}' cause generateObjectFields is deactivated", field.getName());
			return;
		}
		logger.debug("Generating a value for field '{}' with generator {}", field.getName(), generator);

		// random value generation
		final Object propertyValue = generator.create();
		logger.debug("Field '{}' generated value : {}", field.getName(), propertyValue);

		Exception exception = null;
		try {
			// on initialise le champ
			writeField(field, object, propertyValue, true);
		} catch (final IllegalAccessException e) {
			exception = e;
		} catch (final IllegalArgumentException e) {
			exception = e;
		}

		if (exception != null) {
			// on essaie par le writer s'il existe
			try {
				setProperty(object, field.getName(), propertyValue);
			} catch (final IllegalAccessException e1) {
				throw new RuntimeException(exception.getMessage(), e1);
			} catch (final InvocationTargetException e1) {
				throw new RuntimeException(exception.getMessage(), e1);
			} catch (final NoSuchMethodException e1) {
				throw new RuntimeException(exception.getMessage(), e1);
			}
		}
	}

    private boolean shouldSkipField(Field field, Object object) {
      try {
        Object fieldValue = readField(field, object, true);
        if (fieldValue == null) {
          return false;
        }
        
        if (field.getType().isPrimitive()) {
          return !isPrimitiveDefaultValue(field.getType(), fieldValue);
        }

        return true;
        
      } catch (IllegalAccessException e) {
      }
      return false;
    }

    private boolean isPrimitiveDefaultValue(Class<?> fieldType, Object fieldValue) {
      if (boolean.class.equals(fieldType)) {
        return fieldValue.equals(false);
      } else if (float.class.equals(fieldType) || double.class.equals(fieldType)) {
        return String.valueOf(fieldValue).equals("0.0");
      } else {
        return String.valueOf(fieldValue).equals("0");
      }
    }

	protected Generator<?> getGenerator(Field field) {
		Generator<?> generator = fieldGenerators.get(field.getName());
		if (generator == null) {
			generator = toolkit().registry().getGenerator(field.getType());
		}
		if (generator instanceof ContainerGenerator<?>) {
			final Class<?>[] genericTypes = getGenericTypes(field);
			((ContainerGenerator<?>) generator).setElementsTypes(genericTypes);
		}
		fieldGenerators.put(field.getName(), generator);
		return generator;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected T newInstance() {
		T instance = null;
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

				final Class<?>[] parameterTypes = constructor.getParameterTypes();
				final Object[] parameters = toolkit().generate(parameterTypes);

				try {
					instance = (T) constructor.newInstance(parameters);
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
			throw new RuntimeException("Unable to instantiate object of type '" + getObjectClass().getName() + "'",
					lastEx);
		}
		return instance;
	}

	protected Class<?>[] getGenericTypes(Field field) {
		if (!(field.getGenericType() instanceof ParameterizedType)) {
			return null;
		}
		Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
		if (types == null) {
			return null;
		}
		Class<?>[] classes = new Class[types.length];
		for (int i = 0; i < types.length; i++) {
			Type type = types[i];
			classes[i] = (Class<?>) type;
		}
		return classes;
	}

	private static Field[] getAllFields(Object bean) {
		final Class<? extends Object> beanClass = bean.getClass();
		return getAllFields(beanClass);
	}

	private static Field[] getAllFields(Class<? extends Object> beanClass) {
		final Field[] fields = beanClass.getDeclaredFields();
		final Class<?> superclass = beanClass.getSuperclass();
		if (superclass != null) {
			return (Field[]) addAll(fields, getAllFields(superclass));
		} else {
			return fields;
		}
	}

	public void setFieldGenerator(String field, Generator<?> generator) {
		fieldGenerators.put(field, generator);
	}

	public void setSkippedFields(String... field) {
		skippedFields = Arrays.asList(field);
	}

	public void addSkippedField(String field) {
		skippedFields.add(field);
	}

	public void setForcedFields(String... field) {
		forcedFields = field;
	}
}
