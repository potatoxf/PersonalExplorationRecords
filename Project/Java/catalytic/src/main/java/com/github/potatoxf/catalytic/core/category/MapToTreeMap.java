/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.category;

import com.github.potatoxf.catalytic.core.CategoryConverterImpl;
import com.github.potatoxf.catalytic.core.CategoryConverterInput;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author potatoxf
 */
public class MapToTreeMap extends CategoryConverterImpl<TreeMap<String, Object>> {
    private static volatile MapToTreeMap INSTANCE;

    private MapToTreeMap() {
        super(Map.class);
    }

    public static MapToTreeMap getInstance() {
        if (INSTANCE == null) {
            synchronized (MapToTreeMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MapToTreeMap();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Class<Object> loadOriginType() {
        return (Class<Object>) (Class<?>) Map.class;
    }

    @Override
    protected TreeMap<String, Object> apply(Object value, CategoryConverterInput input) throws Throwable {
        TreeMap<String, Object> result = new TreeMap<>(String::compareToIgnoreCase);
        Map<?, ?> map = (Map<?, ?>) value;
        Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
        for (Map.Entry<?, ?> entry : entries) {
            Object k = entry.getKey(), v = entry.getValue();
            if (k == null) k = "null";
            if (k instanceof String) {
                result.put((String) k, v);
            } else {
                result.put(k.toString(), v);
            }
        }
        return result;
    }
}
