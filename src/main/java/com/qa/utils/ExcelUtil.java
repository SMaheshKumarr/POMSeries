package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	
	private static final String EXCEL_PATH="./src/test/resources/testdata/OpenCartLogin.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getExcelData() throws FileNotFoundException {
		//System.out.println("Reading data from sheet: " + sheetName);
		Object[][] data=null;
		try {
			FileInputStream fis=new FileInputStream(EXCEL_PATH);
			book=WorkbookFactory.create(fis);
			sheet=book.getSheetAt(0);
			System.out.println(sheet);
			System.out.println("Row: "+ sheet.getLastRowNum());
			System.out.println("Column: "+sheet.getRow(0).getLastCellNum() );
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			System.out.println(sheet.getRow(0).getCell(0));
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
					data [i][j]=sheet.getRow(i+1).getCell(j).toString();
					System.out.println(data[i][j]);
				}
			}
			
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
		
	}
 
}
