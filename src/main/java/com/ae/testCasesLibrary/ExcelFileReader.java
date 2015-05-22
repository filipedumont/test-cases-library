package com.ae.testCasesLibrary;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelFileReader {

	private Logger logger;
	private List<HSSFWorkbook> workbooks;
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

	private void readWorkbooks(File[] files) {
		workbooks = new ArrayList<HSSFWorkbook>();
		try{
			for(File file : files){
				FileInputStream currentFile = new FileInputStream(file);
				HSSFWorkbook workbook = new HSSFWorkbook(currentFile);
			}
		}catch(IOException e){
			logger.log(Level.SEVERE, e.getMessage());
		}
		
	}

	/**public void readFile(String fileName){
		try{
			FileInputStream file = new FileInputStream(new File(fileName));

		}catch(IOException e){
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	**/

}
