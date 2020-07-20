package com.cc.BQutils;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldList;
import com.google.cloud.bigquery.FieldValueList;

public class BQutils    {

	
	
	
	
	
public BigQuery getBigqueryInstance(String projectId) {

		 return BigQueryOptions.getDefaultInstance().toBuilder().setProjectId(projectId).build().getService();
		   
	}
	

public GenericReportResponseDto genericRowMapper(FieldValueList fieldValueList, FieldList fieldList) {
        GenericReportResponseDto responseDto = null;
        try {
            responseDto = new GenericReportResponseDto();
            for(com.google.cloud.bigquery.Field f : fieldList)
            {
                String fieldName = f.getName();
                if (fieldValueList.get(fieldName).getValue() != null) {                   
                    Object fieldValue = null;
                   
                    switch(f.getType().toString()) {
                   
                        case "INTEGER": fieldValue = fieldValueList.get(fieldName).getLongValue();
                            break;
                        case "FLOAT": fieldValue = fieldValueList.get(fieldName).getDoubleValue();
                            break;
                        case "BOOLEAN": fieldValue = fieldValueList.get(fieldName).getBooleanValue();
                            break;
                        case "DATE": fieldValue = fieldValueList.get(fieldName).getValue();
                            break;
                        case "NUMERIC": fieldValue = fieldValueList.get(fieldName).getNumericValue();
                            break;
                        case "TIMESTAMP": fieldValue = fieldValueList.get(fieldName).getTimestampValue();
                            break;
                        //Default case is for String value
                        default: fieldValue = fieldValueList.get(fieldName).getStringValue();
                    }
                    responseDto.getRowMap().put(fieldName.toLowerCase(), fieldValue);
                }
                else
               
                   responseDto.getRowMap().put(fieldName.toLowerCase(), fieldValueList.get(fieldName).getValue());
            }
           
        } catch (Exception e) {
            System.out.println("Exception message in genericRowMapper() -> " + e.getMessage());
            throw e;
        }
        return responseDto;       
    }







}
