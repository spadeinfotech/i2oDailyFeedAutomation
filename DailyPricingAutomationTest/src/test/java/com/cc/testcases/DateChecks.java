package com.cc.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.cc.ExecuteBQ.Testexecutor;
import com.cc.filereader.ReadWER;
import com.cc.readexcel.ExcelLibrary;
import com.cc.utils.UtilServices;

public class DateChecks {
	 ExcelLibrary lib=new ExcelLibrary();
	ReadWER wer=new ReadWER();
	public UtilServices utilServices = new UtilServices();
	@Test
	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		// System.out.println("under  DateChecks test");
			Testexecutor Te=new Testexecutor();
		 System.out.println("Executing prestgae queries..");
		Te.getAllcolumnsPrestage(this.getClass().getSimpleName());
     Te.getAllcolumnsDataMart(this.getClass().getSimpleName());
     System.out.println("Executing mart queries..");
		 this.utilServices.getPreAndMartDiff(this.getClass().getSimpleName(), lib);

	}

	
}