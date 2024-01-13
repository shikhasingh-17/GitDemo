package Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	public ArrayList getTestData(String testName) throws IOException {
		ArrayList dataList = new ArrayList();
		FileInputStream fis = new FileInputStream("C:\\Users\\yuvra\\OneDrive\\Desktop\\PostmanCollection\\TestData.xlsx");
		
		//1. creating object of XSSFWorkbook class
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//2. get access of specific sheet
		int sheetsCount = workbook.getNumberOfSheets();
		for(int i = 0;i<sheetsCount;i++) {
			//System.out.println(workbook.getSheetName(i));
			if(workbook.getSheetName(i).equalsIgnoreCase("demo")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> sheets = sheet.rowIterator();
				Row firstRow = sheets.next();
				
				Iterator<Cell> cells =  firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while(cells.hasNext()){
					Cell value = cells.next();
					//System.out.println(value.getStringCellValue());
					if (value.getStringCellValue().equalsIgnoreCase("Test")) {
						//System.out.println("Test column found");
						column = k;
					}
					k++;
				}
				System.out.println("Test column number: "+ column);
				
				//find row containing the testName data
				while(sheets.hasNext()) {
					Row r1 = sheets.next();
					if(r1.getCell(column).getStringCellValue().equalsIgnoreCase(testName)) {
						Iterator<Cell> c = r1.cellIterator();
						while(c.hasNext()) {
							Cell data = c.next();
							if(data.getCellType()==CellType.STRING) {
								dataList.add(data.getStringCellValue());
							}
							else {
								dataList.add(data.getNumericCellValue());
							}
						}
						
					}
					
				}
				
			}
		}
		return dataList;
	}
}
