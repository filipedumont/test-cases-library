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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class OldFormatSpreadSheet implements SpreadSheet<HSSFWorkbook, HSSFSheet>{
	private static final Logger logger = Logger.getLogger(OldFormatSpreadSheet.class.getName());
	private HSSFWorkbook workbook;
	private List<HSSFSheet> sheets;

	public void setWorkbook(File f) {
		try {
			FileInputStream file = new FileInputStream(f);
			this.workbook = new HSSFWorkbook(file);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public HSSFWorkbook getWorkbook() {
		logger.log(Level.INFO, "Getting info from workbook...");
		return this.workbook;
	}

	public void setSheets() {
		int sheetsCount = workbook.getNumberOfSheets();
		logger.log(Level.INFO, "Setting sheets from workbook. Sheets found: " + sheetsCount);
		sheets = new ArrayList<HSSFSheet>();
		for(int i = 0; i < sheetsCount; i++){
			HSSFSheet sheet = workbook.getSheetAt(i);
			sheets.add(sheet);
		}
	}

	public HSSFSheet getSheet(int index) {
		logger.log(Level.INFO, "Getting info from sheet " + index + "...");
		return this.sheets.get(index);
	}

	public List<HSSFSheet> getSheets() {
		logger.log(Level.INFO, "Getting sheets from workbook...");
		return this.sheets;
	}

	public void setSheetRows(HSSFSheet sheet) {
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
