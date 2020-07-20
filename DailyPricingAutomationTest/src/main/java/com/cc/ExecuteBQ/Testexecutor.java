package com.cc.ExecuteBQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.cc.BQutils.BQutils;
import com.cc.BQutils.GenericReportResponseDto;
import com.cc.filereader.ReadWER;
import com.cc.readexcel.ExcelLibrary;
import com.cc.startup.Startup;
import com.cc.utils.UtilServices;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldList;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryJobConfiguration.Builder;
import com.google.cloud.bigquery.TableResult;

public class Testexecutor extends Startup {
	public ExcelLibrary lib = new ExcelLibrary();
	public UtilServices utilServices = new UtilServices();
	BQutils Bq = new BQutils();
	ReadWER wer = new ReadWER();
	public BigQuery bigquery = null;
	public BQutils util = new BQutils();

public void GetDatafromPrestageBQ(String scenarioname) throws JobException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
			bigquery = Bq.getBigqueryInstance(Clientid);
		//	System.out.println("Clientid in testcexeutor" + Clientid);
			
			 int Totalteststeps= lib.GetrowcountGeneric(scenarioname,file);
			System.out.println("Totalteststeps in main excel" + Totalteststeps);

			for (int teststep = 1; teststep <= Totalteststeps; teststep++) {

   String queryprestage = wer.getelemnetfromWER(lib.getExcelValuegeneric(scenarioname, teststep, 3,file),".\\config\\PrestageQueries.properties");
				System.out.println("query" + queryprestage);

				// String query = BigQuerySqlStatements.HOME_PAGE_CHARTS;
//	     query =  query +" WHERE org_type=@marketPlace ORDER BY metric_type, period;";
				Builder queryBuilder = QueryJobConfiguration.newBuilder(queryprestage);
				// queryBuilder.addNamedParameter("marketPlace",
				// QueryParameterValue.string(setOrgTypeIdHomeFilter(orgDetails)));
				QueryJobConfiguration queryConfig = queryBuilder.build();
				TableResult returnSet = this.bigquery.query(queryConfig);
				// TableResult returnSet = this.bigQuery.query(queryConfig);
				FieldList fieldList = returnSet.getSchema().getFields();
				// System.out.println("fieldList"+fieldList);

				for (FieldValueList row : returnSet.iterateAll()) {
					// System.out.println("row val="+row.get(columnname).getDoubleValue());

					// GenericReportResponseDto rowObj = util.genericRowMapper(row, fieldList);
					// System.out.println("BQdata"+rowObj.toString());

					String columnname = lib.getExcelValuegeneric(scenarioname, teststep, 4,file);
					System.out.println("columnname" + columnname);
					double querydata = row.get(columnname).getDoubleValue();
					System.out.println("BQdata" + row.get(columnname).getDoubleValue());

					// returnList.add(rowObj.getRowMap());
           lib.setExcelValueIntgeneric(scenarioname, teststep, 5, querydata,file);
						
				}

			}
		

	}

	public void GetDatafromDataMartBQ(String scenarioname)
			throws JobException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {


		bigquery = Bq.getBigqueryInstance(Clientid);
	//	System.out.println("Clientid in testcexeutor" + Clientid);
		
		int Totalteststeps = lib.GetrowcountGeneric(scenarioname,file);
		System.out.println("Totalteststeps in main excel" + Totalteststeps);

		for (int teststep = 1; teststep <= Totalteststeps; teststep++) {

String QueryMart = wer.getelemnetfromWER(lib.getExcelValuegeneric(scenarioname, teststep, 3,file),".\\config\\DataMartQueries.properties");

			// String query = BigQuerySqlStatements.HOME_PAGE_CHARTS;
//     query =  query +" WHERE org_type=@marketPlace ORDER BY metric_type, period;";
			Builder queryBuilder = QueryJobConfiguration.newBuilder(QueryMart);
			// queryBuilder.addNamedParameter("marketPlace",
			// QueryParameterValue.string(setOrgTypeIdHomeFilter(orgDetails)));
			QueryJobConfiguration queryConfig = queryBuilder.build();
			TableResult returnSet = this.bigquery.query(queryConfig);
			// TableResult returnSet = this.bigQuery.query(queryConfig);
			FieldList fieldList = returnSet.getSchema().getFields();
			// System.out.println("fieldList"+fieldList);

			for (FieldValueList row : returnSet.iterateAll()) {
				// System.out.println("row val="+row.get(columnname).getDoubleValue());

				// GenericReportResponseDto rowObj = util.genericRowMapper(row, fieldList);
				// System.out.println("BQdata"+rowObj.toString());
				String columnname = lib.getExcelValuegeneric(scenarioname, teststep, 4,file);
				System.out.println("columnname" + columnname);
				double querydata = row.get(columnname).getDoubleValue();
				System.out.println("BQdata" + row.get(columnname).getDoubleValue());

				// returnList.add(rowObj.getRowMap());

				lib.setExcelValueIntgeneric(scenarioname, teststep, 6, querydata,file);

				// Setcounttoexcel.Setvaltoexcelwithexception(BQdata, screenname, teststep++);

				// System.out.println( rowObj.getRowMap().get("brand_code"));

			}

		}

	}

	public void getAllcolumnsDatastatic(String scenarioname)
			throws JobException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		int Totalteststeps = lib.GetrowcountGeneric(scenarioname,file);
		System.out.println("Totalteststeps in main excel" + Totalteststeps);
		String querydata1 = null;
		String querydata2 = null;

		bigquery = Bq.getBigqueryInstance(wer.getelemnetfromWER("projectid", ".\\config\\config.properties"));

		String queryprestage = wer.getelemnetfromWER(lib.getExcelValuegeneric(scenarioname, 1, 3,file),
				".\\config\\PrestageQueries.properties");
		System.out.println("query" + queryprestage);

		// String query = BigQuerySqlStatements.HOME_PAGE_CHARTS;
// query =  query +" WHERE org_type=@marketPlace ORDER BY metric_type, period;";
		Builder queryBuilder = QueryJobConfiguration.newBuilder(queryprestage);
		// queryBuilder.addNamedParameter("marketPlace",
		// QueryParameterValue.string(setOrgTypeIdHomeFilter(orgDetails)));
		QueryJobConfiguration queryConfig = queryBuilder.build();
		TableResult returnSet = this.bigquery.query(queryConfig);
		// TableResult returnSet = this.bigQuery.query(queryConfig);
		FieldList fieldList = returnSet.getSchema().getFields();
		// System.out.println("fieldList" + fieldList);
		List<Map<String, Object>> rowObjList = new ArrayList<>();
		GenericReportResponseDto rowObj = null;

		for (FieldValueList row : returnSet.iterateAll()) {
//			String columnname = lib.getExcelValue(scenarioname, teststep, 4);
			// System.out.println("columnname"+columnname);

			rowObj = util.genericRowMapper(row, fieldList);
			// System.out.println("BQdata all columsn" + rowObj);
			querydata1 = rowObj.getRowMap().get("product_code").toString();
			querydata2 = rowObj.getRowMap().get("cogs").toString();
			System.out.println(rowObj.getRowMap().get("product_code"));
			System.out.println(rowObj.getRowMap().get("cogs"));
			rowObjList.add(rowObj.getRowMap());

			// double querydata= row.get(columnname).getDoubleValue();
			// System.out.println("BQdata"+row.get(columnname).getDoubleValue());

			// returnList.add(rowObj.getRowMap());

		}

		if (rowObjList.size() > 0) {
			for (int teststep = 1; teststep <= rowObjList.size(); teststep++) {
				System.out.println("xlrowssizes" + rowObjList.size());
				lib.setExcelValue(scenarioname, teststep, 4,
						rowObjList.get(teststep - 1).get("product_code").toString());
				lib.setExcelValue(scenarioname, teststep, 5, rowObjList.get(teststep - 1).get("cogs").toString());

			}
		}

	}

	public void getAllcolumnsPrestage(String scenarioname)
			throws JobException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		try {
		bigquery = Bq.getBigqueryInstance(Clientid);
		//System.out.println("Clientid in testcexeutor" + Clientid);
		int Totalteststeps = lib.GetrowcountGeneric(scenarioname,file);
		//System.out.println("Totalteststeps in main excel" + Totalteststeps);

for (int teststep = 1; teststep <= Totalteststeps; teststep++) {
	//System.out.println("teststep" + teststep);
			String queryLogicalName = lib.getExcelValuegeneric(scenarioname, teststep, 3,file).toString();
			if (!queryLogicalName.equalsIgnoreCase("") && queryLogicalName != null) {
			//	System.out.println(queryLogicalName);
	String  queryprestage = wer.getelemnetfromWER(queryLogicalName,".\\config\\PrestageQueries.properties");
	//System.out.println("queryfromxl"+queryprestage);
	
	
	/*
	Boolean isExists=queryfromxl.contains("Scrape_Date");
String queryprestage=null;
if(isExists==true) {
	System.out.println("inside 0 if");
	 queryprestage=utilServices.ReplaceString(queryfromxl);
}
else {
	System.out.println("inside else");
	queryprestage=queryfromxl;
}
    
System.out.println("queryafterreplace"+queryprestage);
*/
	
	String queryprestage_columns = wer.getelemnetfromWER(queryLogicalName + "_columns",".\\config\\PrestageQueries.properties");
				//System.out.println("queryprestage_columns" + queryprestage_columns);
	
				String[] queryprestage_columnList = queryprestage_columns.split(",");
				Builder queryBuilder = QueryJobConfiguration.newBuilder(queryprestage);
				QueryJobConfiguration queryConfig = queryBuilder.build();
				TableResult returnSet = this.bigquery.query(queryConfig);
			
				FieldList fieldList = returnSet.getSchema().getFields();
				
				List<Map<String, Object>> rowObjList = new ArrayList<>();

				for (FieldValueList row : returnSet.iterateAll()) {
				
					GenericReportResponseDto rowObj = util.genericRowMapper(row, fieldList);
					rowObjList.add(rowObj.getRowMap());
					//System.out.println("BQdata all columsn" + rowObj);

				}
				int writeStep = teststep;

				if (rowObjList.size() > 0) {
					for (int n = 0, teststep1 = 1; teststep1 <= rowObjList.size(); teststep1++, n++) {
						
						Map<String, Object> row = rowObjList.get(n);
					//	System.out.println("row"+row);
					//	System.out.println("index 0" + row.get(queryprestage_columnList[0]));
					//	System.out.println("index 1" + row.get(queryprestage_columnList[1]));
				
				lib.setExcelValueGeneric(scenarioname, writeStep, 4, row.get(queryprestage_columnList[0]).toString(),file);
				lib.setExcelValueGeneric(scenarioname, writeStep, 5,  row.get(queryprestage_columnList[1]).toString(),file);
					
						writeStep++;
					}
				
				}
		
			}
	
		}
		}	catch (Exception e) {
		  	// TODO Auto-generated catch block
		  	e.printStackTrace();
		       }	
	}


	
public void getAllcolumnsDataMart(String scenarioname)
			throws JobException, InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
	
		try {
	bigquery = Bq.getBigqueryInstance(Clientid);
	//	System.out.println("Clientid in testcexeutor" + Clientid);
		int Totalteststeps = lib.GetrowcountGeneric(scenarioname,file);

		//System.out.println("Totalteststeps in main excel" + Totalteststeps);
		
		for (int teststep = 1; teststep <= Totalteststeps; teststep++) {
			String queryLogicalName = lib.getExcelValuegeneric(scenarioname, teststep, 3,file).toString();
			if (!queryLogicalName.equalsIgnoreCase("") && queryLogicalName != null) {	
			//	System.out.println(queryLogicalName);

	String QueryMart = wer.getelemnetfromWER(queryLogicalName,".\\config\\DataMartQueries.properties");
/*	
	Boolean isExists=queryfromxl.contains("Scrape_Date");
	String QueryMart=null;
	if(isExists==true) {
		System.out.println("inside 0 if");
		 QueryMart=utilServices.ReplaceString(queryfromxl);
	}
	else {
		System.out.println("inside else");
		QueryMart=queryfromxl;
	}
	
*/
		 
	String QueryMart_columns = wer.getelemnetfromWER(queryLogicalName + "_columns",".\\config\\DataMartQueries.properties");
				String[] QueryMart_columnList = QueryMart_columns.split(",");

				Builder queryBuilder = QueryJobConfiguration.newBuilder(QueryMart);

				QueryJobConfiguration queryConfig = queryBuilder.build();
				TableResult returnSet = this.bigquery.query(queryConfig);
				// TableResult returnSet = this.bigQuery.query(queryConfig);
				FieldList fieldList = returnSet.getSchema().getFields();
				// System.out.println("fieldList" + fieldList);

				List<Map<String, Object>> rowObjList = new ArrayList<>();

				for (FieldValueList row : returnSet.iterateAll()) {

					GenericReportResponseDto rowObj = util.genericRowMapper(row, fieldList);
					rowObjList.add(rowObj.getRowMap());
				}

				int writeStep = teststep;
		if (rowObjList.size() <= 1) {
					for (int n = 0, teststep1 = 1; teststep1 <= rowObjList.size(); teststep1++, n++) {
					
						Map<String, Object> row = rowObjList.get(n);
						
						lib.setExcelValueGeneric(scenarioname, writeStep, 6,  row.get(QueryMart_columnList[1]).toString(),file);
					//System.out.println("column-1"+row.get(QueryMart_columnList[1]).toString());
					
						writeStep++;
					}
				
				}				
		else if (rowObjList.size() > 1) {
			   for (int n = 0,teststep1 = 1 ; teststep1 <= rowObjList.size(); teststep1++, n++) {
				   Map<String, Object> row = rowObjList.get(n);	
				  // System.out.println("row"+row);
					//System.out.println("index 0" + row.get(QueryMart_columnList[0]));
				//	System.out.println("index 1" + row.get(QueryMart_columnList[1]));
		         for (String column : QueryMart_columnList) { 			
			     lib.setExcelValueGeneric(scenarioname, writeStep, 6,  row.get(column).toString(),file);
			    // System.out.println("column-1 "+row.get(column).toString());
		            }
		           writeStep++;	
							 }
		                 }

		
			}
			
			
		}
		}	catch (Exception e) {
		  	// TODO Auto-generated catch block
		  	e.printStackTrace();
		       }	
	}

	
}
