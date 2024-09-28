/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.utils.reflect;

import com.github.potatoxf.catalytic.utils.FunctionClassValue;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author potatoxf
 */
public final class Reflects {
    public static final ClassValue<Map<MemberDescriptor<?>, Method>> CACHE_METHOD;
    public static final ClassValue<Map<MemberDescriptor<?>, Field>> CACHE_FIELD;
    public static final ClassValue<Constructor<?>> CACHE_DEFAULT_CONSTRUCTOR;
    public static final ClassValue<Map<AttributeName, AttributeInfo>> ATTRIBUTE_INFO;
    private static final ClassValue<Field[]>[] CACHE_FIELDS = new ClassValue[8];
    private static final ClassValue<Method[]>[] CACHE_METHODS = new ClassValue[8];

    static {
        CACHE_DEFAULT_CONSTRUCTOR = FunctionClassValue.withInitial(type -> {
            try {
                return type.getConstructor();
            } catch (NoSuchMethodException ignored) {
                return null;
            }
        });
        CACHE_METHOD = FunctionClassValue.withInitial(type -> {
            Map<MethodDescriptor, Method> map = new HashMap<>();
            for (Class<?> c = type; c != Object.class; c = c.getSuperclass()) {
                for (Method e : c.getDeclaredMethods()) {
                    Integer modifiers = e.getModifiers();
                    if (!Modifier.isPublic(modifiers)) {
                        try {
                            e.setAccessible(true);
                        } catch (Throwable ignored) {
                        }
                    }
                    map.put(new MethodDescriptor(e.getName(), c, modifiers, e.getGenericReturnType(), e.getParameterTypes()), e);
                }
            }
            return Collections.unmodifiableMap(map);
        });

        CACHE_FIELD = FunctionClassValue.withInitial(type -> {
            Map<FieldDescriptor, Field> map = new HashMap<>();
            for (Class<?> c = type; c != Object.class; c = c.getSuperclass()) {
                for (Field e : c.getDeclaredFields()) {
                    Integer modifiers = e.getModifiers();
                    if (!Modifier.isPublic(modifiers)) {
                        try {
                            e.setAccessible(true);
                        } catch (Throwable ignored) {
                        }
                    }
                    map.put(new FieldDescriptor(e.getName(), c, modifiers, e.getGenericType()), e);
                }
            }
            return Collections.unmodifiableMap(map);
        });

        Reflects.setFieldClassValue(false, false, false);
        Reflects.setFieldClassValue(true, false, false);
        Reflects.setFieldClassValue(false, true, false);
        Reflects.setFieldClassValue(true, true, false);
        Reflects.setFieldClassValue(false, false, true);
        Reflects.setFieldClassValue(true, false, true);
        Reflects.setFieldClassValue(false, true, true);
        Reflects.setFieldClassValue(true, true, true);

        Reflects.setMethodClassValue(false, false, false);
        Reflects.setMethodClassValue(true, false, false);
        Reflects.setMethodClassValue(false, true, false);
        Reflects.setMethodClassValue(true, true, false);
        Reflects.setMethodClassValue(false, false, true);
        Reflects.setMethodClassValue(true, false, true);
        Reflects.setMethodClassValue(false, true, true);
        Reflects.setMethodClassValue(true, true, true);

        ATTRIBUTE_INFO = FunctionClassValue.withInitial(type -> {
            Map<MemberDescriptor<?>, Field> fieldMap = CACHE_FIELD.get(type);
            Map<MemberDescriptor<?>, Method> methodMap = CACHE_METHOD.get(type);

            Method[] methods = CACHE_METHODS[computeSequenceIndex(true, false, false)].get(type);
            Field[] fields = CACHE_FIELDS[computeSequenceIndex(true, false, true)].get(type);

            Set<AttributeName> attributeNames = new HashSet<>();
            for (Field e : fields) attributeNames.add(AttributeName.ofField(e));
            for (Method e : methods) attributeNames.add(AttributeName.ofMethod(e));
            attributeNames.remove(null);

            Map<AttributeName, AttributeInfo> map = new HashMap<>();
            for (AttributeName attributeName : attributeNames) {
                LookupDescriptor fieldLookup = new LookupDescriptor(attributeName.toLowerCamelCase());

                Field field = fieldMap.get(fieldLookup);
                Class<?> fieldType = field != null ? field.getType() : null;

                LookupDescriptor getMethodLookup = new LookupDescriptor("get" + attributeName.toUpperCamelCase(), fieldType);
                Method getMethod = methodMap.get(getMethodLookup);
                if (getMethod == null && (fieldType == Boolean.class || fieldType == boolean.class)) {
                    getMethodLookup = new LookupDescriptor("is" + attributeName.toUpperCamelCase(), type);
                    getMethod = methodMap.get(getMethodLookup);
                }

                LookupDescriptor setMethodLookup = new LookupDescriptor("set" + attributeName.toUpperCamelCase(), fieldType);
                Method setMethod = methodMap.get(setMethodLookup);

                map.put(attributeName, new AttributeInfo(attributeName, field, getMethod, setMethod));
            }
            return map;
        });
    }

    public static <T> T newInstance(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> defaultConstructor = CACHE_DEFAULT_CONSTRUCTOR.get(clazz);
        if (defaultConstructor == null) {
            throw new NoSuchMethodException("No default constructor found for " + clazz);
        }
        return (T) defaultConstructor.newInstance();
    }

    /**
     * 计算序列索引，索引范围0到n*2-1
     */
    private static int computeSequenceIndex(boolean... conditions) {
        int v = 0;
        for (int i = 0; i < conditions.length; i++) {
            v += (conditions[i] ? 1 << (i) : 0);
        }
        return v;
    }

    public static Map<AttributeName, AttributeInfo> listAttributeMap(Class<?> clz) {
        return ATTRIBUTE_INFO.get(clz);
    }

    public static List<Field> listFields(Class<?> clz, boolean includeSuperclass, boolean includeStatic, boolean includePrivate) {
        return Arrays.asList(CACHE_FIELDS[computeSequenceIndex(includeSuperclass, includeStatic, includePrivate)].get(Objects.requireNonNull(clz)));
    }

    public static List<Method> listMethods(Class<?> clz, boolean includeSuperclass, boolean includeStatic, boolean includePrivate) {
        return Arrays.asList(CACHE_METHODS[computeSequenceIndex(includeSuperclass, includeStatic, includePrivate)].get(Objects.requireNonNull(clz)));
    }

    public static Map<MemberDescriptor<?>, Field> listFieldMap(Class<?> clz) {
        return CACHE_FIELD.get(Objects.requireNonNull(clz));
    }

    public static Map<MemberDescriptor<?>, Method> listMethodMap(Class<?> clz) {
        return CACHE_METHOD.get(Objects.requireNonNull(clz));
    }

    private static void setFieldClassValue(boolean includeSuperclass, boolean includeStatic, boolean includePrivate) {
        CACHE_FIELDS[computeSequenceIndex(includeSuperclass, includeStatic, includePrivate)] = FunctionClassValue.withInitial(type -> {
            Map<MemberDescriptor<?>, Field> map = Reflects.listFieldMap(type);
            ArrayList<Field> results;
            if (includeSuperclass && includeStatic && includePrivate) {
                results = new ArrayList<>(map.values());
            } else {
                results = new ArrayList<>();
                for (Map.Entry<MemberDescriptor<?>, Field> entry : map.entrySet()) {
                    MemberDescriptor<?> descriptor = entry.getKey();
                    if (!includeSuperclass && descriptor.atLocation(type)) continue;
                    if (!includeStatic && descriptor.isStatic()) continue;
                    if (!includePrivate && !descriptor.isPublic()) continue;
                    results.add(entry.getValue());
                }
            }
            return results.toArray(new Field[0]);
        });
    }

    private static void setMethodClassValue(boolean includeSuperclass, boolean includeStatic, boolean includePrivate) {
        CACHE_METHODS[computeSequenceIndex(includeSuperclass, includeStatic, includePrivate)] = FunctionClassValue.withInitial(type -> {
            Map<MemberDescriptor<?>, Method> map = Reflects.listMethodMap(type);
            ArrayList<Method> results;
            if (includeSuperclass && includeStatic && includePrivate) {
                results = new ArrayList<>(map.values());
            } else {
                results = new ArrayList<>();
                for (Map.Entry<MemberDescriptor<?>, Method> entry : map.entrySet()) {
                    MemberDescriptor<?> descriptor = entry.getKey();
                    if (!includeSuperclass && descriptor.atLocation(type)) continue;
                    if (!includeStatic && descriptor.isStatic()) continue;
                    if (!includePrivate && !descriptor.isPublic()) continue;
                    results.add(entry.getValue());
                }
            }
            return results.toArray(new Method[0]);
        });
    }

    /**
     * @param index
     * @return
     */
    public static <T> Class<?> lookupSuperclassGenericType(Class<T> suberclass, Class<? super T> superclass, int index) {
        LinkedList<Class<?>> types = new LinkedList<>();
        for (Class<?> c = suberclass; c != superclass; c = c.getSuperclass())
            types.add(c);
        Class<?> foundType = Object.class;
        int arr = 0;
        while (!types.isEmpty()) {
            Class<?> currentType = types.pollLast();
            Type superclassGeneric = currentType.getGenericSuperclass();
            if (superclassGeneric instanceof Class) {
                break;
            } else {
                Type type = ((ParameterizedType) superclassGeneric).getActualTypeArguments()[index];
                boolean finish = false;
                while (true) {
                    if (type instanceof GenericArrayType) {
                        type = ((GenericArrayType) type).getGenericComponentType();
                        arr++;
                    } else {
                        if (type instanceof TypeVariable) {
                            TypeVariable<? extends Class<?>>[] typeParameters = currentType.getTypeParameters();
                            for (int i = 0; i < typeParameters.length; i++) {
                                if (typeParameters[i] == type) {
                                    index = i;
                                    try {
                                        foundType = (Class<?>) typeParameters[i].getBounds()[0];
                                    } catch (ClassCastException ignored) {
                                    }
                                    break;
                                }
                            }
                        } else {
                            if (type instanceof Class) {
                                foundType = (Class<?>) type;
                            } else if (type instanceof ParameterizedType) {
                                foundType = (Class<?>) ((ParameterizedType) type).getRawType();
                            }
                            finish = true;
                        }
                        break;
                    }
                }
                if (finish) break;
            }
        }

        if (arr != 0) {
            foundType = Array.newInstance(foundType, new int[arr]).getClass();
        }
        return foundType;
    }
}
