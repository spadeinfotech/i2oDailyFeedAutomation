package com.cc.startup;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cc.readexcel.ExcelLibrary;
import com.cc.utils.UtilServices;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
public class Startup  {
	
   public static  String Clientid=null;
	public static String file=null;
	public static String scenarioname=null;
	
	
@BeforeClass(enabled= true)
public void copytestcaseforexe() throws IOException {
	 System.out.println("under before class..");
	UtilServices utilServices = new UtilServices();
	  File testcaserepo = new File(".\\Repo");
      File testcaseexe = new File(".\\Testcases");
       UtilServices.copyFolder(testcaserepo, testcaseexe);
}

@AfterClass(enabled= true)
public  void MovetestReportoarchive() throws IOException {
	 System.out.println("under afer class..");
	 UtilServices utilServices = new UtilServices();
	File sourceFolder = new File(".\\Testcases");
    Date now = new Date();
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh mm ss");
		String time = dateFormat.format(now);
		 File dest = new File(".\\Testreport_archive\\Testreport"+ time);
		 dest.mkdir();
       UtilServices.copyFolder(sourceFolder, dest);

}


  @Test(enabled= true)
  public void RuntestSuite() throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
	 
	  ExcelLibrary lib=new ExcelLibrary();
	    
	  int TotalClients = lib.Getrowcountfromclientfile("Clients");
	//  System.out.println("Totalclients in  excel" + TotalClients);

	  
for (int client = 1; client <= TotalClients; client++) {
String Clientexecutionstatus=lib.getExcelValuegeneric("Clients", client, 1,".\\config\\Clients.xlsx");
//System.out.println("Clientexecutionstatus"+Clientexecutionstatus);


if(Clientexecutionstatus.equalsIgnoreCase("Yes")){

	Clientid=lib.getExcelValuegeneric("Clients", client, 0,".\\config\\Clients.xlsx");		

	System.out.println("********************Test Is running for:-"+Clientid+"**********************************");
	  int  totaltests =0;
	  	  
if(Clientid.equals("i2o-dev-su"))	{
		  file=".\\Testcases\\i2o_TestReport_SU.xlsx";
          totaltests = lib.Getrowcountgeneric("TestModules",file);
         // System.out.println("totaltests"+totaltests);
			  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
           String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
			  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
			  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
	System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
			  UtilServices.callTestcases();
          lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

			  }
			  	
			  	}			
		}
else if(Clientid.equals("i2o-dev-dell"))	{
	  file=".\\Testcases\\i2o_TestReport_Dell.xlsx";
      totaltests = lib.Getrowcountgeneric("TestModules",file);
		  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
       String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
		  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
		  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
		  UtilServices.callTestcases();
      lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

		  }
		  	
		  	}			
	}
else if(Clientid.equals("i2o-dev-victrola"))	{
	  file=".\\Testcases\\i2o_TestReport_Victrola.xlsx";
    totaltests = lib.Getrowcountgeneric("TestModules",file);
		  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
     String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
		  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
		  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
		  UtilServices.callTestcases();
    lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

		  }
		  	
		  	}			
	}	  
else if(Clientid.equals("i2o-dev-ecovacs"))	{
	  file=".\\Testcases\\i2o_TestReport_Ecovacs.xlsx";
  totaltests = lib.Getrowcountgeneric("TestModules",file);
		  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
   String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
		  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
		  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
		  UtilServices.callTestcases();
  lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

		  }
		  	
		  	}			
	}	
else if(Clientid.equals("i2o-dev-jvc"))	{
	  file=".\\Testcases\\i2o_TestReport_Jvc.xlsx";
    totaltests = lib.Getrowcountgeneric("TestModules",file);
		  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
     String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
		  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
		  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
		  UtilServices.callTestcases();
    lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

		  }
		  	
		  	}			
	}

else if(Clientid.equals("i2o-dev-ausgold"))	{
	  file=".\\Testcases\\i2o_TestReport_Ausgold.xlsx";
totaltests = lib.Getrowcountgeneric("TestModules",file);
		  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
String scenariosexecutionstatus=lib.getExcelValuegeneric("TestModules", testscenario, 1,file);
		  	if(scenariosexecutionstatus.equalsIgnoreCase("yes")) {
		  scenarioname=lib.getExcelValueGeneric("TestModules", testscenario, 0,file);
System.out.println("********************Executing TestCases for:-"+scenarioname+"***************************");
		  UtilServices.callTestcases();
lib.setExcelValueGeneric("TestModules", testscenario, 2, lib.getdateandtime(),file);

		  }
		  	
		  	}			
	}	  
	  
	  
	  
	  
	  
	  
	  
	  
	 	  
	  
  }
}


	}
}