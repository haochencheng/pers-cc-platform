package pers.platfrom.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getValue(String key) {
		Properties	prop=new Properties();
		InputStream in=PropertiesUtil.class.getResourceAsStream("/Properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	
	
}
