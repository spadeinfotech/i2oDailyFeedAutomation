package com.cc.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.JUnitCore;

import com.cc.filereader.ReadWER;
import com.cc.readexcel.ExcelLibrary;
import com.cc.startup.Startup;
import com.google.common.io.Files;

public class UtilServices extends Startup {
	static ReadWER wer = new ReadWER();
	
public static boolean isNumeric(String preval, String martval) {
    if (preval == null && martval== null ) {
	        return false;
	    }
	    try {
	        double a = Double.parseDouble(preval);
	        double b= Double.parseDouble(martval);
	       
   // System.out.println("diff=="+a-b);
	 
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}	
public static void callTestcases() throws ClassNotFoundException   {
JUnitCore runTest = new JUnitCore();
		  @SuppressWarnings("rawtypes")
		Class c;
	c = Class.forName("com.cc.testcases."+scenarioname);
	   //System.out.println("Running tescase:-"+c);
			runTest.run(c);
	}
	
public void deletefolder() {
	 File dest=new File(".\\Testcases\\as");
	// dest.mkdir();
	  String[]entries = dest.list();
	  for(String s: entries){
	      File currentFile = new File(dest.getPath(),s);
	      currentFile.delete();
	  }
	  
	  System.out.println("directory is "+dest.isDirectory());
	if(dest.isDirectory()) {
		dest.delete();
	}
	dest.mkdir();
	 
}	

public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException
{
    //Check if sourceFolder is a directory or file
    //If sourceFolder is file; then copy the file directly to new location
    if (sourceFolder.isDirectory()) 
    {
        //Verify if destinationFolder is already present; If not then create it
        if (!destinationFolder.exists()) 
        {
            destinationFolder.mkdir();
            //System.out.println("Directory created :: " + destinationFolder);
        }
         
        //Get all files from source directory
        String files[] = sourceFolder.list();
         
        //Iterate over all files and copy them to destinationFolder one by one
        for (String file : files) 
        {
            File srcFile = new File(sourceFolder, file);
            File destFile = new File(destinationFolder, file);
             
            //Recursive function call
            copyFolder(srcFile, destFile);
        }
    }
    else
    {
        //Copy the file content from one place to another 
      // 
    	Files.copy(sourceFolder, destinationFolder);
    	//.copy(sourceFolder, destinationFolder, StandardCopyOption.REPLACE_EXISTING);
       // System.out.println("File copied :: " + destinationFolder);
    }
}


public void getPreAndMartDiff(String scenarioname, ExcelLibrary lib) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
	  try {
			//System.out.println("inside check");
	   String PrestageVal = null;
		String Martval = null;
		Double countdifference = 0.0;
		int Totalteststeps = lib.Getrowcountgeneric(scenarioname,file);
		
		//System.out.println("Totalteststeps in main excel" + Totalteststeps);	
	for (int teststep = 1; teststep <= Totalteststeps; teststep++) {	
		//System.out.println("under for");
		String queryLogicalName = lib.getExcelValuegeneric(scenarioname, teststep, 3,file).toString();
		String Productcode = lib.getExcelValuegeneric(scenarioname, teststep, 4,file).toString();
		PrestageVal = lib.getExcelValuegeneric(scenarioname, teststep, 5, file);
		Martval = lib.getExcelValuegeneric(scenarioname, teststep, 6, file);
		
	boolean input=	isNumeric(PrestageVal,Martval);
		
	if(input==true) {
		//System.out.println("inside number checking");	
		  double a = Double.parseDouble(PrestageVal);
		 // System.out.println("a"+a);
		
	        double b= Double.parseDouble(Martval);
	       // System.out.println("b"+b);
	      
		countdifference = a - b;
		lib.setExcelValueIntgeneric(scenarioname, teststep, 7, countdifference,file);

		if (countdifference == 0.0) {
			//System.out.println("matching condition");
			lib.setExcelValueGeneric(scenarioname, teststep, 8, "Pass",file);

		} else if (Double.compare(countdifference, Double.valueOf(0.0)) > 0
				|| Double.compare(countdifference, Double.valueOf(0.0)) < 0) {
			lib.setExcelValueGeneric(scenarioname, teststep, 8, "Fail",file);
			//System.out.println("not matching condition");
			
			System.out.println(Clientid+"|"+queryLogicalName+":- Mismatch found for"+" "+Productcode);
			//System.out.println("MisMatch in Prestgae and Mart Data="+countdifference);
		
			
		}
			
	}
		
	
	
	else {
		//System.out.println("inside string checking ");		
	//System.out.println("PrestageVal"+PrestageVal);
	//System.out.println("Martval"+Martval);
	if (PrestageVal.equals(Martval) == true) {
	
		lib.setExcelValueGeneric(scenarioname, teststep, 8, "Pass",file);

	} else  {
		lib.setExcelValueGeneric(scenarioname, teststep, 8, "Fail",file);
		//System.out.println("not matching condition");
		
		System.out.println( queryLogicalName+" "+"is getting failed for="+Clientid);
	//	System.out.println("MisMatch in Prestgae and Mart Data="+countdifference);
	}	
		
		}
	}
	
		}	catch (Exception e) {
		  	// TODO Auto-generated catch block
		  	e.printStackTrace();
		       }	
	}
	
	



public String ReplaceString(String Query) throws IOException {
	String ratval=null;
	Scanner sc = new Scanner(Query);
    StringBuffer buffer = new StringBuffer();
    while (sc.hasNextLine()) {
       buffer.append(sc.nextLine()+System.lineSeparator());
    }
    String bufferquery = buffer.toString();
    System.out.println("Contents of the file in method: "+bufferquery);
    sc.close();
   // String oldLine = "'2020-05-17' and '2020-05-23'";
    String oldScrapedate=wer.getelemnetfromWER("OldScrape_Date",".\\config\\config.properties");
    String newScrapedate=wer.getelemnetfromWER("NewScrape_Date",".\\config\\config.properties");
    bufferquery = bufferquery.replaceAll(oldScrapedate, newScrapedate);
    ratval=bufferquery;

	return ratval;
}






}
