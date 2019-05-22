package com.example.config;

import com.example.enums.EnumType;
import com.example.mybatis.EnumTypeHandler;
import com.example.utils.ClassUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        try {
            final List<Class<?>> allAssignedClass = ClassUtil.getAllAssignedClass(EnumType.class);
            allAssignedClass.forEach((clazz) -> {
                typeHandlerRegistry.register(clazz, EnumTypeHandler.class);
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}