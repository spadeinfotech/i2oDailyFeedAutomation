package com.cc.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.cc.ExecuteBQ.Testexecutor;
import com.cc.filereader.ReadWER;
import com.cc.readexcel.ExcelLibrary;
import com.cc.utils.UtilServices;
import com.google.api.client.util.DateTime;

public class DailyPricingTest3P {
	 ExcelLibrary lib=new ExcelLibrary();
	ReadWER wer=new ReadWER();
	public UtilServices utilServices = new UtilServices();
	@Test
	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		// System.out.println("under  DailyPricingTest3P test");
			Testexecutor Te=new Testexecutor();
			 System.out.println("Executing prestgae queries..");
		Te.getAllcolumnsPrestage(this.getClass().getSimpleName());
			 System.out.println("Executing Mart queries..");
       Te.getAllcolumnsDataMart(this.getClass().getSimpleName());
		
		this.utilServices.getPreAndMartDiff(this.getClass().getSimpleName(), lib);
		
		        
			 
		        
		        
	}

	
}