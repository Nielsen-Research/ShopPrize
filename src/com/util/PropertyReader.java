package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private static PropertyReader instance=null;
	Properties prop = null;
	
	
	public static PropertyReader getInstance() {
        if(instance == null) {
           
                instance = new PropertyReader();
           
        }
        return instance;
    }

	public void load() throws IOException {
		InputStream inputStream;
		try {
			
			String propFileName = "resources/config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop = new Properties();
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			inputStream.close();
		
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		
	}
	
	public String getProperty(String name)
	{
		if (prop==null){
            try {
				load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return prop.getProperty(name);
		
	}
	
	
	
	

}
