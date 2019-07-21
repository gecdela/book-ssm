package com.mybatis.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.mybatis.beans.MapParam;
import com.mysql.jdbc.Statement;

@Intercepts({@Signature(method="handleResultSets",type = ResultSetHandler.class, args={ Statement.class })})
public class MapInterceptor implements Interceptor {
	//日志    
	private static final Logger logger = LoggerFactory.getLogger(MapInterceptor.class); 
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub

		// 获取代理目标对象
		Object target = invocation.getTarget();
		if (target instanceof DefaultResultSetHandler) {
			DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler) target;
			// 利用反射获取参数对象
			ParameterHandler parameterHandler = reflect(resultSetHandler);
			Object parameterObj = parameterHandler.getParameterObject();
			// 参数对象为MapParam进入处理逻辑
			if (parameterObj instanceof MapParam) {
				MapParam mapParam = (MapParam) parameterObj;
				// 获取当前statement
				Statement stmt = (Statement) invocation.getArgs()[0];
				// 根据maoParam返回处理结果
				return handleResultSet(stmt.getResultSet(), mapParam);
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	} 
	private Object handleResultSet(ResultSet resultSet, MapParam mapParam) {
		if (null != resultSet) {
			// 获取key field name
			String keyFieldName = (String) mapParam.get(MapParam.KEY_FIELD);
			// 获取value field name
			String valueFieldName = (String) mapParam.get(MapParam.VALUE_FIELD);
			// 值类型
			String valueClass = (String) mapParam.get(MapParam.VALUE_CLASS);
			List<Object> resultList = new ArrayList<Object>();
			Map<Object, Object> map = new HashMap<Object, Object>();
			try {
				while (resultSet.next()) {
					Object key = resultSet.getObject(keyFieldName);
					Object value;
					// 根据值类型转换值
					if (StringUtils.equals(valueClass, MapParam.ValueClass.INTEGER.getCode())) {
						value = resultSet.getInt(valueFieldName);
					} else if (StringUtils.equals(valueClass, MapParam.ValueClass.BIG_DECIMAL.getCode())) {
						value = resultSet.getBigDecimal(valueFieldName);
					} else {
						value = resultSet.getObject(valueFieldName);
					}
					map.put(key, value);
				}
			} catch (SQLException e) {
				logger.error("map interceptor转换异常，{}", e.getMessage());
			} finally { // 关闭result set
				closeResultSet(resultSet);
			}
			resultList.add(map);
			return resultList;
		}
		return null;
	}

	private void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			logger.error("关闭 result set异常,{}", e.getMessage());
		}
	}

	private ParameterHandler reflect(DefaultResultSetHandler resultSetHandler) {
		Field field = ReflectionUtils.findField(DefaultResultSetHandler.class, "parameterHandler");
		field.setAccessible(true);
		Object value = null;
		try {
			value = field.get(resultSetHandler);
		} catch (Exception e) {
			logger.error("默认返回结果集反射参数对象异常，{}", e.getMessage());
		}
		return (ParameterHandler) value;
	}

	}


