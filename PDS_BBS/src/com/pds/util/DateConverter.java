package com.pds.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (toType == Date.class) {// 当字符串向Date类型转换时
			String[] params = (String[]) value;// request.getParameterValues()
			try {
				return dateFormat.parseObject(params[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (toType == String.class) {// 当Date转换成字符串时
			Date date = (Date) value;
			return dateFormat.format(date);
		}
		return null;
	}
}
