package com.ae.testCasesLibrary;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       ExcelFileReader excel = new ExcelFileReader();
       
       excel.readFiles(args[0]);
    }
}
