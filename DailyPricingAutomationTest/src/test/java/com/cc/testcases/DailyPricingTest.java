package com.cc.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.cc.ExecuteBQ.Testexecutor;
import com.cc.filereader.ReadWER;
import com.cc.readexcel.ExcelLibrary;
import com.cc.utils.UtilServices;

public class DailyPricingTest {
	 ExcelLibrary lib=new ExcelLibrary();
	ReadWER wer=new ReadWER();
	public UtilServices utilServices = new UtilServices();
	@Test
	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		 
			Testexecutor Te=new Testexecutor();
			 System.out.println("Executing prestgae queries..");
			Te.getAllcolumnsPrestage(this.getClass().getSimpleName());
			 System.out.println("Executing mart queries..");
               Te.getAllcolumnsDataMart(this.getClass().getSimpleName());
		this.utilServices.getPreAndMartDiff(this.getClass().getSimpleName(), lib);

	}

	
}