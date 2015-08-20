package com.sheep.common.converter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.sheep.common.utils.StringUtil;

/**
 * CustomConverter
 * <p/>
 * obj->Map;Map->obj
 * 
 * @author xiejianqiao
 * @Timestamp 2015年7月31日 下午2:40:36
 * @version 1.0
 */
public class CustomConverter {
	
	/**
	 * 中文描述：对象转换成Map
	 */
	public static Map<String, Object> covertToMap(Object bean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Class<?> clazz = bean.getClass();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] descriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : descriptors) {
				Method getter = descriptor.getReadMethod();
				Object returnValue = getter.invoke(bean);
				if (returnValue != null) {
					if ("-1".equals(returnValue)) {
						continue;
					}
					if (returnValue.getClass() == String.class) {
						if (StringUtil.isNotBlank(returnValue.toString())) {
							result.put(descriptor.getName(), returnValue
									.toString().trim());
						}
					} else {
						result.put(descriptor.getName(), returnValue);
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 中文描述：对象转换成Key=Value串
	 */
	public static String covertToQueryStr(Object bean) {
		String query = "";
		Class<?> clazz = bean.getClass();
		BeanInfo beanInfo;
		try {
			StringBuilder sb = new StringBuilder();
			beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] descriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : descriptors) {
				Method getter = descriptor.getReadMethod();
				Object returnValue = getter.invoke(bean);
				if (returnValue != null) {
					if ("-1".equals(returnValue)) {
						continue;
					}
					if (returnValue.getClass() == String.class) {
						if (StringUtil.isNotBlank(returnValue.toString())) {
							sb.append(descriptor.getName())
									.append("=")
									.append(URLEncoder.encode(returnValue
											.toString().trim(), "UTF-8"))
									.append("&");
						}
					} else {
						sb.append(descriptor.getName())
								.append("=")
								.append(URLEncoder.encode(returnValue
										.toString().trim(), "UTF-8"))
								.append("&");
					}
				}
			}
			query = sb.toString();
			if(query.endsWith("&")){
				query = query.substring(0, sb.toString().lastIndexOf("&"));
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return query;
	}
	
	
}
