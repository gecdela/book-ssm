package com.mybatis.beans;

import java.util.HashMap;

public class MapParam extends HashMap<Object, Object> { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY_FIELD = "keyField"; 
	// value���� 
	public static final String VALUE_FIELD = "valueField"; 
	// valueֵ����  
	public static final String VALUE_CLASS = "valueClass"; 
	public MapParam(){
		
	} 
	public MapParam(String keyField, String valueField, String valueClass)
	{ 
		this.put(KEY_FIELD, keyField); 
		this.put(VALUE_FIELD, valueField); 
		this.put(VALUE_CLASS, valueClass); 
	} 
	// valueֵ����ö���� 
	public enum ValueClass 
	{
		INTEGER("integer"),
		BIG_DECIMAL("bigDecimal");
		private String code; 
		public String getCode() 
		{ 
			return code;
		}
		ValueClass(String code)
		{ 
			this.code = code;
			}
		} 
	}


