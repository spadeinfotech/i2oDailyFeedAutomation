package com.cc.filereader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadWER {


public String getelemnetfromWER(String logicalname, String propfilepath) throws IOException {
	String ratval=null;
	String filepath=propfilepath;
	FileInputStream fis=new FileInputStream(filepath);
	Properties prop=new Properties();
	
	prop.load(fis);
	
ratval=	prop.getProperty(logicalname);
	
	return ratval;
}



}
