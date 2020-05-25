package my.FrameWork.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

public class ExcelManager {

	Workbook book;
	Sheet sheet;
	Row row;
	Cell cell;
	String path = System.getProperty("user.dir") + "\\data\\data.xlsx";

	public ExcelManager() {
		try {
			File f = new File(path);
			FileInputStream fi = new FileInputStream(f);
			book = new XSSFWorkbook(fi);
			sheet = book.getSheet("Sheet1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public int getRowCount() {
		return sheet.getLastRowNum() + 1;
	}

	public int getClmnCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	@SuppressWarnings("null")
	public Object[][] getData(String testName) {
		String[] methods = testName.split(",");
		Hashtable<String, String> data = null;
		Object data1[][] = null;
		int index = 0;
		// getRowCount()
		data1 = new Object[methods.length][1];
		// No of test iteration.
		for (int k = 0; k < methods.length; k++) {
			data = new Hashtable<String, String>();
			for (int i = 1; i < getRowCount(); i++) {
				String actTM = sheet.getRow(i).getCell(0).getStringCellValue();
				if (methods[k].equals(actTM)) {
					for (int j = 1; j < getClmnCount(); j++) {
						String key1 = sheet.getRow(0).getCell(j).getStringCellValue();
						String value1 = sheet.getRow(i).getCell(j).getStringCellValue();
						// data1.put(key1, value1);
						data.put(key1, value1);
					}
					data1[index][0] = data;
					System.out.println("Data read done for " + methods[k]);
					index++;
					break;
				}
			}
		}
		return data1;
	}

	public Object[][] getTestData(String testName) {

		Hashtable<String, String> data = null;
		Object data1[][] = null;
		data1 = new Object[getRowCount()][0];
		for (int i = 1; i < getRowCount(); i++) {
			data = new Hashtable<String, String>();
			String actTM = sheet.getRow(i).getCell(0).getStringCellValue();
			if (testName.equals(actTM)) {
				for (int j = 1; j < getClmnCount(); j++) {
					String key1 = sheet.getRow(0).getCell(j).getStringCellValue();
					String value1 = sheet.getRow(i).getCell(j).getStringCellValue();
					// data1.put(key1, value1);
					data.put(key1, value1);
				}
			}
			data1[1][0] = data;
		}

		return data1;

	}

}
