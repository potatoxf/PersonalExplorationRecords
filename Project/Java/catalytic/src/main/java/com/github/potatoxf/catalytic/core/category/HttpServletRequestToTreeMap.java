/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.core.category;

import com.github.potatoxf.catalytic.core.CategoryConverterImpl;
import com.github.potatoxf.catalytic.core.CategoryConverterInput;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author potatoxf
 */
public class HttpServletRequestToTreeMap extends CategoryConverterImpl<TreeMap<String, Object>> {
    private static volatile HttpServletRequestToTreeMap INSTANCE;
    private Method getParameterMapMethod;

    private HttpServletRequestToTreeMap() {
        super(null);
    }

    public static HttpServletRequestToTreeMap getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpServletRequestToTreeMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpServletRequestToTreeMap();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean isLoadedCorrectlyConverter() {
        return super.isLoadedCorrectlyConverter() && getParameterMapMethod != null;
    }

    @Override
    protected Class<Object> loadOriginType() {
        Class<?> originType = null;
        try {
            originType = Class.forName("javax.servlet.ServletRequest");
            getParameterMapMethod = originType.getMethod("getParameterMap");
        } catch (Throwable ignored) {
        }
        if (originType == null) {
            try {
                originType = Class.forName("jakarta.servlet.ServletRequest");
                getParameterMapMethod = originType.getMethod("getParameterMap");
            } catch (Throwable ignored) {
            }
        }
        return (Class<Object>) originType;
    }

    @Override
    protected TreeMap<String, Object> apply(Object value, CategoryConverterInput input) throws Throwable {
        TreeMap<String, Object> result = new TreeMap<>(String::compareToIgnoreCase);
        Map<String, String[]> map = (Map<String, String[]>) getParameterMapMethod.invoke(value);
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String k = entry.getKey();
            if (k == null) k = "null";
            String[] vs = entry.getValue();
            if (vs == null || vs.length == 0) {
                result.put(k, "");
            } else if (vs.length == 1) {
                result.put(k, vs[0]);
            } else {
                int i = 0;
                for (String v : vs) {
                    if (v == null || v.isEmpty()) i++;
                }
                if (vs.length == i) {
                    result.put(k, "");
                } else if (vs.length - 1 == i) {
                    result.put(k, vs[0]);
                } else {
                    result.put(k, vs);
                }
            }
        }
        return result;
    }
}
