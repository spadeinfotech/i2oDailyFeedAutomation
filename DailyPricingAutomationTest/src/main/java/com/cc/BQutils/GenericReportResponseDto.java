package com.cc.BQutils;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenericReportResponseDto {
	 private Map<String, Object> rowMap = new LinkedHashMap<String, Object>();

	public Map<String, Object> getRowMap() {
		return rowMap;
	}

	public void setRowMap(Map<String, Object> rowMap) {
		this.rowMap = rowMap;
	}

	@Override
	public String toString() {
		return "" + rowMap ;
	}  
	 
	 
	 //A row in the form of a map of columnName and columnValue
}
