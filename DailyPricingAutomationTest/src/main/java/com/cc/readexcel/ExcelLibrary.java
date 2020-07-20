package com.cc.readexcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {

	public static String filepath = ".\\Testcases\\testscenarios.xlsx";

	public int GetrowcountGeneric(String sheetname, String file) throws EncryptedDocumentException, InvalidFormatException, IOException {
		int ratval = 0;
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet(sheetname);
		ratval = s.getLastRowNum();
		return ratval;

	}

	public String getExcelValueGeneric(String sheetName, int rowNum, int cellNum,String  file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String retValue = null;

		try {
			FileInputStream fis = new FileInputStream(file);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.getCell(cellNum);
			retValue = c.getStringCellValue();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retValue;

	}

	public double getExcelValueIntGeneric(String sheetName, int rowNum, int cellNum,String  file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		double retValue = 0.0;

		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();

			// Row r = s.getRow(rowNum);
			// Cell c = r.getCell(cellNum);
			String val = formatter.formatCellValue(s.getRow(rowNum).getCell(cellNum));
			// String cellvalue = c.getStringCellValue();
			// retValue=Integer.parseInt(cellvalue);
			retValue = Double.parseDouble(val);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retValue;
	}

	public int getrowcount(String sheetname)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		int ratval = 0;

		FileInputStream fis = new FileInputStream(filepath);

		Workbook wb = WorkbookFactory.create(fis);

		Sheet s = wb.getSheet(sheetname);
		ratval = s.getLastRowNum();

		return ratval;
	}

	@SuppressWarnings("deprecation")
	public void setExcelValueGeneric(String sheetName, int rowNum, int cellNum, String val, String file) {

		try {
			FileInputStream fis = new FileInputStream(file);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.createCell(cellNum);
			// c.setCellType(Cell.CELL_TYPE_NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellType(CellType.STRING);
			c.setCellValue(val);
//System.out.println("val in exlmethof"+val);
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// the below method need to be removed
	@SuppressWarnings("deprecation")
	public void setExcelValue(String sheetName, int rowNum, int cellNum, String val) {

		try {
			FileInputStream fis = new FileInputStream(filepath);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			if (r == null) {
				return;
			}
			Cell c = r.createCell(cellNum);
			// c.setCellType(Cell.CELL_TYPE_NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellType(CellType.STRING);
			c.setCellValue(val);

			FileOutputStream fos = new FileOutputStream(filepath);
			wb.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@SuppressWarnings("deprecation")
	public void setExcelValueInt(String sheetName, int rowNum, int cellNum, double val)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		try {
			FileInputStream fis = new FileInputStream(filepath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.createCell(cellNum);
			c.setCellType(CellType.NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(val);
			FileOutputStream fos = new FileOutputStream(filepath);
			wb.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void setExcelValueIntgeneric(String sheetName, int rowNum, int cellNum, double val, String file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		//System.out.println("writing in file" + file);

		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.createCell(cellNum);
			c.setCellType(CellType.NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_NUMERIC);
			// c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(val);
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getdateandtime() {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String ratval = dateFormat.format(date);
		return ratval;

	}

	public String storetextvalue(String textvalue) {
		String ratval = null;

		ArrayList<String> textvalues = new ArrayList<String>();
		textvalues.add(textvalue);

		return ratval;

	}

	public int Getrowcountfromclientfile(String sheetname)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String clientfile = ".\\config\\Clients.xlsx";
		int ratval = 0;
		FileInputStream fis = new FileInputStream(clientfile);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet(sheetname);
		ratval = s.getLastRowNum();
		return ratval;

	}

	public String getExcelValuegeneric(String sheetName, int rowNum, int cellNum, String file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String retValue = null;

		try {
			FileInputStream fis = new FileInputStream(file);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
		
			Row r = s.getRow(rowNum);
	
			Cell c = r.getCell(cellNum);
			//System.out.println("c"+c);
			retValue = c.getStringCellValue();
			//System.out.println("retValue"+retValue);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retValue;

	}

	public int Getrowcountgeneric(String sheetname, String file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		int ratval = 0;
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet(sheetname);
		ratval = s.getLastRowNum();
		return ratval;

	}

}
