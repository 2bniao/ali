package com.mt.tools;

import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConstantsConfig 
{

	private static PropertiesConfiguration config = null;
	static 
	{
		
		try {

			config = new PropertiesConfiguration();
			config.setEncoding("UTF-8"); 
			config.load("serverConfig.properties");
			
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} 
	}

	public static Configuration getConfigure() {
		return config;
	}
	
	public static String getString(String key)
	{
		return config.getString(key);
	}
	
	public static String getString(String key,String defaultValue)
	{
		return config.getString(key,defaultValue);
	}
	public static long getLong(String key)
	{
		return config.getLong(key);
	}
	
	public static long getLong(String key,long defaultValue)
	{
		return config.getLong(key,defaultValue);
	}
	
	public static int getInt(String key)
	{
		return config.getInt(key);
	}
	
	public static int getInt(String key,int defaultValue)
	{
		return config.getInt(key,defaultValue);
	}
	
	public static boolean getBoolean(String key)
	{
		return config.getBoolean(key);
	}
	
	public static boolean getBoolean(String key,boolean defaultValue)
	{
		return config.getBoolean(key,defaultValue);
	}
	
	public static byte getByte(String key)
	{
		return config.getByte(key);
	}
	public static byte getByte(String key,byte defaultValue)
	{
		return config.getByte(key,defaultValue);
	}
	
	public static double getDouble(String key)
	{
		return config.getDouble(key);
	}
	public static double getDouble(String key,double defaultValue)
	{
		return config.getDouble(key, defaultValue);
	}
	public static List getList(String key)
	{
		return config.getList(key);
	}
	
}
