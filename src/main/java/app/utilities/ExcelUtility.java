package app.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
 private static ExcelUtility excelutility =null;
 
 private static XSSFSheet ExcelWSheet;
 
 private static XSSFWorkbook ExcelWBook;

 private static XSSFCell Cell;	
 	
 private static XSSFRow Row;	
 
 private ExcelUtility()
 {
	
 }
 
 public static ExcelUtility getInstance() {
	 return (excelutility==null) ? excelutility=new ExcelUtility():excelutility;
 }
 
 public void readExcel() {
	 String filepath= ConfigManager.getInstance().getProperty("ExcelPath");
	 try {
		 	
			ExcelWBook = new XSSFWorkbook(new FileInputStream(filepath));
			ExcelWSheet = ExcelWBook.getSheet(ConfigManager.getInstance().getProperty("ExcelSheet"));
		} catch ( IOException e) {
			System.out.println("Excel file not found at path : " + filepath );
			e.printStackTrace();
		}
	 int rowCount = ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
	 
	 for (int i = 0; i < rowCount+1; i++) {

	        Row = ExcelWSheet.getRow(i);

	        //Create a loop to print cell values in a row

	        for (int j = 0; j < Row.getLastCellNum(); j++) {

	            //Print Excel data in console

	            System.out.print(Row.getCell(j).getStringCellValue()+"|| ");

	        }

	        System.out.println();
	    } 
 }
 
 public String fetchdata(String locatorname) {
	 String locator= null;
	 String filepath= ConfigManager.getInstance().getProperty("ExcelPath");
	 try {
		 	
			ExcelWBook = new XSSFWorkbook(new FileInputStream(filepath));
			ExcelWSheet = ExcelWBook.getSheet(ConfigManager.getInstance().getProperty("Locator"));
		} catch ( IOException e) {
			System.out.println("Excel file not found at path : " + filepath );
			e.printStackTrace();
		}
	 int rowCount = ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
	 
	 for (int i = 0; i < rowCount+1; i++) {

	        Row = ExcelWSheet.getRow(i);

	        if (Row.getCell(0).getStringCellValue().equals(locatorname))
	        {
	        	locator=Row.getCell(2).getStringCellValue();
	        }

	    } 
	 return locator;
 }
 public static void main (String [] args)
	{
		System.out.println(ExcelUtility.getInstance().fetchdata("Locator_1"));
	}
}
