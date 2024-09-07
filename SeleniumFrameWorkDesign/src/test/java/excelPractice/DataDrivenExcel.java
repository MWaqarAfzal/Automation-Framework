package excelPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenExcel {
	
	public ArrayList<String> getData(String testName) throws IOException
	{
		
		ArrayList<String> list = new ArrayList<String>();
		//		Get the path of the Excel file
		FileInputStream fis = new FileInputStream("C://Users//wafzal//Desktop//Test Data file.xlsx");
		//		Create an object of Excel file and pass the file path inside it
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//		Get the count of the sheet available in the file
		int sheetCount = workbook.getNumberOfSheets();
		System.out.println("The excel book sheet count is " + sheetCount);
		
		//		Access the sheet and do the operation on it
		for(int i=0; i<sheetCount; i++)
		{
			if (workbook.getSheetName(i).equalsIgnoreCase("sampledata"))
			{
				//	Identify Test Case column by scanning the entire 1st Row
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();		// Sheet is the collection of Rows
				Row firstrow = rows.next();
				
				Iterator<Cell> cell = firstrow.cellIterator();	// Row is the collection of Cells
				int k=0;
				int column=0;
				while(cell.hasNext())
				{
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("testcase"))
					{
						column=k;
					}
					k++;
				}
				System.out.println(column);
				
				
				//	Once the column index is identified now scan the entire TestCase column to identified the purchase order
				
				while(rows.hasNext())
				{
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testName))
					{
						Iterator<Cell> cell2 = r.cellIterator();
						while(cell2.hasNext())
						{
							Cell cellData = cell2.next();
							if(cellData.getCellType()==CellType.STRING)
							{
								list.add(cellData.getStringCellValue());
							}
							else
							{
								//	Convert the Numeric Data into String
								list.add(NumberToTextConverter.toText(cellData.getNumericCellValue()));
							}

						}
						
					}
				}
				
			}
		}
		return list;
	}
}

