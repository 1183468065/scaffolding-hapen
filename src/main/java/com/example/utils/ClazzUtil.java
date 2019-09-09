package com.example.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

public class ClazzUtil {
    public static Map<String, Object> convertBean(Object bean) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            Class<?> type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * 获取在指定包下某个class的所有非抽象子类
     *
     * @param parentClass 父类
     * @param packagePath 指定包，格式如"com/sinosun/tarvel"
     * @return 该父类对应的所有子类列表
     */
    public static <E> List<Class<E>> getSubClasses(final Class<E> parentClass, final String packagePath) throws ClassNotFoundException {
        final ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(parentClass));
        final Set<BeanDefinition> components = provider.findCandidateComponents(packagePath);
        final List<Class<E>> subClasses = new ArrayList<>();
        for (final BeanDefinition component : components) {
            @SuppressWarnings("unchecked") final Class<E> cls = (Class<E>) Class.forName(component.getBeanClassName());
            if (Modifier.isAbstract(cls.getModifiers())) {
                continue;
            }
            subClasses.add(cls);
        }
        return subClasses;
    }
}
