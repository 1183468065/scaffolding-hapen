package com.example.mybatis;

import com.example.enums.EnumType;
import com.example.enums.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@MappedTypes(value = {Status.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private static final Logger logger = LoggerFactory.getLogger(EnumTypeHandler.class);
    private Class<E> type;
    private Map<Integer, E> map = new HashMap<>();

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enums) {
            EnumType enumType = (EnumType) e;
            map.put(enumType.code(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        EnumType enumType = (EnumType) parameter;
        ps.setInt(i, enumType.code());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumType(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumType(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnumType(i);
        }
    }

    private E getEnumType(int value) {
        E result = null;
        try {
            result = map.get(value);
            if (null == result) {
                throw new Exception();
            }
        } catch (Exception ex) {
            logger.error("Cannot convert {} to {} by value.", value, type.getSimpleName());
        }
        return map.get(value);
    }
}