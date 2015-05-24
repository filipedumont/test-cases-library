package com.ae.testCasesLibrary;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.ae.spreadsheetObject.NewFormatSpreadSheet;
import com.ae.spreadsheetObject.OldFormatSpreadSheet;
import com.ae.spreadsheetObject.SpreadSheet;

public class ExcelFileReader {

	private static final Logger logger = Logger.getLogger(ExcelFileReader.class.getName());
	private List<SpreadSheet> workbooks;
	private List<HSSFSheet> sheet;
	
	private class ExcelFileFilter implements FileFilter{

		private final String[] acceptedExtensions = new String[]{"xls", "xlsx"};
		public boolean accept(File file) {
			for(String extension : acceptedExtensions){
				if(file.getName().toLowerCase().endsWith(extension)){
					return true;
				}
			}
			return false;
		}	
	}

	public void readFiles(String path){
		
		int countFiles = 0;
		File f = new File(path);
		logger.log(Level.INFO, "Current directory: " + f.getAbsolutePath());
		File[] files = f.listFiles(new ExcelFileFilter());
		if(null != files){
			for(int i = 0; i < files.length; i++){
				countFiles++;
				File currentFile = files[i];
				if (currentFile.isDirectory()){
					readFiles(currentFile.getAbsolutePath());
				}
			}
		}
		logger.log(Level.INFO, "Files in the directory: "+ countFiles);
		readWorkbooks(files);

	}
	
	private void readWorkbooks(File[] files){
		workbooks = new ArrayList<SpreadSheet>();
		for(File file : files){
			String format = getFileExtension(file);
			workbooks.add(loadWorkbook(format, file));
		}
	}
	
	private SpreadSheet<?, ?> loadWorkbook(String type, File file){
		SpreadSheet<?, ?> spreadsheet = null;
		if(".xlsx".equals(type))
		{
			spreadsheet = new NewFormatSpreadSheet();
		}else if(".xls".equals(type)){
			spreadsheet = new OldFormatSpreadSheet();
		}
		spreadsheet.setWorkbook(file);
		return spreadsheet;
	}
	
	/**
	 * Need to work on this function later
	 * @param file
	 * @return format of the current file
	 */
	private String getFileExtension(File f){
		String path = f.getAbsolutePath();
		String fileFormat = path.substring(path.lastIndexOf('.'));
		return fileFormat;
	}

}
