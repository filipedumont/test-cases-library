package com.ae.spreadsheetObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewFormatSpreadSheet implements SpreadSheet<XSSFWorkbook, XSSFSheet>{

	private static final Logger logger = Logger.getLogger(NewFormatSpreadSheet.class.getName());
	private XSSFWorkbook workbook;
	private List<XSSFSheet> sheets;

	public void setWorkbook(File f) {
		try{
			FileInputStream file = new FileInputStream(f);
			this.workbook = new XSSFWorkbook(file);
			setSheets(this.workbook);
		}catch(IOException e){
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public XSSFWorkbook getWorkbook() {
		logger.log(Level.INFO, "Getting info from workbook...");
		return this.workbook;
	}

	private void setSheets(XSSFWorkbook workbook) {
		int sheetsCount = workbook.getNumberOfSheets();
		logger.log(Level.INFO, "Setting sheets from workbook. Sheets found: " + sheetsCount);
		sheets = new ArrayList<XSSFSheet>();
		for(int i = 0; i < sheetsCount; i++){
			XSSFSheet sheet = workbook.getSheetAt(i);
			setSheetRows(sheet);// wtf
			sheets.add(sheet);
		}
	}

	public XSSFSheet getSheet(int index) {
		logger.log(Level.INFO, "Getting info from sheet " + index + "...");
		return this.sheets.get(index);
	}

	public List<XSSFSheet> getSheets() {
		logger.log(Level.INFO, "Getting sheets from workbook...");
		return this.sheets;
	}

	public void setSheetRows(XSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();

		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			System.out.println("Row: "+ row.getRowNum());
			setRowCells(row);
		}
	}

	public void setRowCells(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		while(cellIterator.hasNext()){
			Cell cell = cellIterator.next();
			System.out.println("Cell: " + cell.getColumnIndex() + "\nValue: "+ cell.getStringCellValue());
		}
	}

}
