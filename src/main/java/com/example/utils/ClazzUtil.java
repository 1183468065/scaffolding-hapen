package com.example.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClazzUtil {
	public static Map<String,Object> convertBean(Object bean){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			Class<?> type = bean.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
			for (int i = 0; i< propertyDescriptors.length; i++) {
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
}
