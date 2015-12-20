package com.pds.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;

public class ConverUtil {

	/**
	 * 把vo转成pojo
	 * (参数为vo的实例，待转化的pojo的具体类名）
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static Object converVo2PojoByClass(Object source_instance, Class<?> target_class){
		//先获取source_instance/target中的字段集合
		Field []source_instanceFields = source_instance.getClass().getDeclaredFields();
		Field []target_instanceFields = target_class.getDeclaredFields();
		List<Field> list = intersection(source_instanceFields, target_instanceFields);
		Object targetInstance = null;
		// 获得属性的首字母并转换为大写，与setXXX/getXXX对应
		try {
			targetInstance =  Class.forName(target_class.getName()).newInstance();
//			targetInstance = target_class.getConstructor(new Class[] {}).newInstance(new Object[] {});
			for (Field field : list) {
				String fieldName = field.getName();
			    String firstLetter = fieldName.substring(0, 1).toUpperCase();
			    //获取target的set方法
			    String setMethodName = "set" + firstLetter + fieldName.substring(1);
			    Method setMethod = target_class.getMethod(setMethodName,new Class[] { field.getType() });
			    //获取source_instance的get方法
			    String getMethodName = "get" + firstLetter + fieldName.substring(1);
			    Method getMethod = source_instance.getClass().getMethod(getMethodName);
			    //调用对象的setXXX方法
			    setMethod.invoke(targetInstance, new Object[] { getMethod.invoke(source_instance) });
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetInstance;
	}
	
	public static Object converVo2PojoByClass1(Object source_instance, Class<?> target_class){
		//先获取source_instance/target中的字段集合
		Field []source_instanceFields = source_instance.getClass().getDeclaredFields();
		Field []target_instanceFields = target_class.getDeclaredFields();
		List<Field> list = intersection(source_instanceFields, target_instanceFields);
		Object targetInstance = null;
		try {
			targetInstance =  Class.forName(target_class.getName()).newInstance();
			for (Field field : list) {
				String fieldName = field.getName();
				PropertyUtils.setProperty(targetInstance, fieldName, PropertyUtils.getProperty(source_instance, fieldName));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetInstance;
	}
	
	/**
	 * 把vo转成pojo  
	 * (参数为vo的实例，待转化的pojo的实例）
	 */
	public static<T> T converVo2PojoByInstance(T source_instance, T target_instance){
		//先获取source_instance/target中的字段集合
		Field []source_instanceFields = source_instance.getClass().getDeclaredFields();
		Field []target_instanceFields = target_instance.getClass().getDeclaredFields();
		List<Field> list = intersection(source_instanceFields, target_instanceFields);
		// 获得属性的首字母并转换为大写，与setXXX/getXXX对应
		try {
			for (Field field : list) {
				String fieldName = field.getName();
			    String firstLetter = fieldName.substring(0, 1).toUpperCase();
			    //获取target的set方法
			    String setMethodName = "set" + firstLetter + fieldName.substring(1);
			    Method setMethod = target_instance.getClass().getMethod(setMethodName,new Class[] { field.getType() });
			    //获取source_instance的get方法
			    String getMethodName = "get" + firstLetter + fieldName.substring(1);
			    Method getMethod = source_instance.getClass().getMethod(getMethodName);
			    //调用对象的setXXX方法
			    setMethod.invoke(target_instance, new Object[] { getMethod.invoke(source_instance) });
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) target_instance;
	}
	
	
	/**
	 * 把vo转成pojo  
	 * (参数为vo的实例，待转化的pojo的实例）
	 */
	public static<T> T converVo2PojoByInstance1(T source_instance, T target_instance){
		//先获取source_instance/target中的字段集合
		Field []source_instanceFields = source_instance.getClass().getDeclaredFields();
		Field []target_instanceFields = target_instance.getClass().getDeclaredFields();
		List<Field> list = intersection(source_instanceFields, target_instanceFields);
		try {
			for (Field field : list) {
				String fieldName = field.getName();
				PropertyUtils.setProperty(target_instance, fieldName, PropertyUtils.getProperty(source_instance, fieldName));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) target_instance;
	}
	
	
	/**
	 * 求两个集合中元素的交集
	 */
	public static<T> List<T> intersection(T []array1, T[]array2){
		List<T> array = new ArrayList<T>();
		T []l_array =array1.length > array2.length ? array1 : array2;
		T []s_array =array1.length < array2.length ? array1 : array2;
		int longest = l_array.length;
		int shortest = s_array.length;
		for(int i =0 ;i < longest; i++){
			for(int j = 0;j< shortest; j++){
				//如果被比较的是字段
				if(l_array[i].getClass().getName().equals("java.lang.reflect.Field")
						&&s_array[j].getClass().getName().equals("java.lang.reflect.Field")){
					if(((Field) l_array[i]).getName().equals(((Field) s_array[j]).getName()))
					array.add(l_array[i]);
				}else{
					if(l_array[i].equals(s_array[j])){
						array.add(l_array[i]);
					}
				}
			}
		}
		return array;
	} 
}
