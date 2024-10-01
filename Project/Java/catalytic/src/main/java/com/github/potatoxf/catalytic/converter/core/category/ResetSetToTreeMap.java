/*
 * Copyright (c) 2024.
 */

package com.github.potatoxf.catalytic.converter.core.category;

import com.github.potatoxf.catalytic.converter.core.CategoryConverterImpl;
import com.github.potatoxf.catalytic.converter.core.CategoryConverterInput;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author potatoxf
 */
public class ResetSetToTreeMap extends CategoryConverterImpl<List<TreeMap<String, Object>>> {
    private static volatile ResetSetToTreeMap INSTANCE;

    private ResetSetToTreeMap() {
        super(ResultSet.class);
    }

    public static ResetSetToTreeMap getInstance() {
        if (INSTANCE == null) {
            synchronized (ResetSetToTreeMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ResetSetToTreeMap();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected List<TreeMap<String, Object>> apply(Object value, CategoryConverterInput input) throws Throwable {
        ResultSet resultSet = (ResultSet) value;
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String columnLabel, columnName, name;
        List<String> names = new ArrayList<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            columnLabel = metaData.getColumnLabel(i);
            columnName = metaData.getColumnName(i);
            if (columnName != null && columnName.equals(columnLabel)) {
                name = columnName;
            } else {
                name = columnLabel;
            }
            names.add(name);
        }

        List<TreeMap<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            TreeMap<String, Object> map = new TreeMap<>(String::compareToIgnoreCase);
            for (int i = 0; i < columnCount; i++) {
                map.put(names.get(i), resultSet.getObject(i + 1));
            }
            result.add(map);
        }
        return result;
    }
}
